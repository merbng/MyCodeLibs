package com.app.merbng.mycodelibs.A_drag_swip;

import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 类似支付宝应用管理界面
 * 实现拖拽滑动
 * http://www.jianshu.com/p/02a9b697fdc0
 */
public class DragSwipeActivity extends BaseActivity implements NewsView, SwipeRefreshLayout.OnRefreshListener, OnNewsChannelListener {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;
    LoadingTip mLoadedTip;

    private List<NewsChannelTable> mTables = new ArrayList<>();
    private List<NewsInfo> mInfos = new ArrayList<>();
    private NewsListAdapter mNewsListAdapter;
    //当前加载新闻类型
    private String cur_news_type = "headline";
    //当前加载新闻类型的id
    private String cur_news_id = "T1348647909107";
    //当前加载新闻页数
    private int cur_news_page = 0;
    //设置第一次网络请求
    private boolean isFirst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_swipe);
        initView();
    }

    private void initView() {
        mRecyclerView=  ((RecyclerView) findViewById(R.id.recyclerViewDrag));
        mRefreshLayout=  ((SwipeRefreshLayout) findViewById(R.id.refreshLayout));
        mLoadedTip=  ((LoadingTip) findViewById(R.id.loadedTip));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRefreshLayout.setOnRefreshListener(this);
        //设置下拉刷新的按钮的颜色
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        setLoadMoreListener();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    @Subscribe
    public void onFabScrollEvent(FabScrollBean event){
        mRecyclerView.smoothScrollToPosition(0);
    }
    @Subscribe
    public void onHeaderEvent(HeaderBean event){
        returnNewsChannel(event.getList());
    }
    private void setLoadMoreListener() {
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的item position
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    // 判断是否滚动到底部并且是向下滑动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        cur_news_page +=20;
                        System.out.println("加载页数："+cur_news_page);
                    }
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;

            }
        });

    }

    @Override
    public void returnNewsChannel(List<NewsChannelTable> tables) {
        System.out.println(tables);
        System.out.println("频道个数:"+tables.size());
        mTables.clear();
        mTables.addAll(tables) ;
        addAllChannel();
        if(mNewsListAdapter!=null){
            mNewsListAdapter.setTables(mTables);
            mNewsListAdapter.notifyItemChanged(0);
        }

    }

    @Override
    public void returnNewsList(List<NewsInfo> infos) {
        mInfos = infos;
        if (!isFirst) {
            isFirst = true;
            mNewsListAdapter = new NewsListAdapter(mContext, mInfos, mTables);
            mNewsListAdapter.setNewsChannelListener(this);
            mRecyclerView.setAdapter(mNewsListAdapter);
        } else if (cur_news_page == 0) {
            mRefreshLayout.setRefreshing(false);
            List<NewsInfo> data = mNewsListAdapter.getAdapterData();
            data.clear();
            data.addAll(infos);
            System.out.println("刷新后的数据：" + data.size());
            mNewsListAdapter.notifyDataSetChanged();
        } else if (cur_news_page > 0) {
            infos.remove(0);
            List<NewsInfo> data = mNewsListAdapter.getAdapterData();
            data.addAll(infos);
            System.out.println("加载后的数据：" + data.size());
            mNewsListAdapter.notifyDataSetChanged();
        }


    }

    /**
     * 添加跳转到管理新闻频道的界面
     */
    private void addAllChannel() {
        if (mTables != null) {
            NewsChannelTable allChannel = new NewsChannelTable("全部", "", "", false, 0, true, R.drawable.news_all);
            mTables.add(allChannel);
        }
    }


    public void showLoading() {
        mLoadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
    }

    public void stopLoading() {
        mLoadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void onRefresh() {
        cur_news_page = 0;
        mRefreshLayout.setRefreshing(true);

    }


    @Override
    public void changeChannelListener(NewsChannelTable newsChannelTable) {
        String type = newsChannelTable.getNewsChannelType();
        String id = newsChannelTable.getNewsChannelId();
        cur_news_type = type;
        cur_news_id = id;
        cur_news_page = 0;
        System.out.println(cur_news_type);
        System.out.println(cur_news_id);
    }


}
