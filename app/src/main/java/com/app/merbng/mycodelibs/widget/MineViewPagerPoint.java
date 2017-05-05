package com.app.merbng.mycodelibs.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.A_TumbleEditText.utils.ToastUtil;
import com.app.merbng.mycodelibs.R;

import java.util.ArrayList;


/**圆角椭圆 -导航栏
 * Created by ght on 2016/12/4.
 */

public class MineViewPagerPoint extends LinearLayout {
    private int childCount = 0;
    private ArrayList<String> listData = new ArrayList<>();

    public MineViewPagerPoint(Context context) {
        super(context);
        init();
    }

    public void setListData(ArrayList<String> listData) {
        this.listData = listData;
    }

    public MineViewPagerPoint(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MineViewPagerPoint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MineViewPagerPoint(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
//        setOrientation(HORIZONTAL);
    }

    private void setText(int i, TextView tv) {
        if (i == 0) {
            tv.setText("发布");
        } else if (i == 1) {
            tv.setText("评论");
        } else if (i == 2) {
            tv.setText("投票");
        } else {
            tv.setText("收藏");
        }
    }

    private void setListDataText(int i, TextView tv) {
        String string = listData.get(i);
        tv.setText(string);
    }

    private void setThreeText(int i, TextView tv) {
        if (i == 0) {
            tv.setText("发布");
        } else if (i == 1) {
            tv.setText("评论");
        } else {
            tv.setText("收藏");
        }
    }

    LayoutParams tv_params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.99f);
    LayoutParams tv_params_wrap = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    LayoutParams line_params_wrap = new LayoutParams(2, LayoutParams.WRAP_CONTENT);
    LayoutParams line_params = new LayoutParams(2, LayoutParams.WRAP_CONTENT, 0.01f);
    LayoutParams ll_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);

    private LinearLayout getLL() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setId(R.id.mine_ll_total);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setLayoutParams(ll_params);
        return linearLayout;
    }

    private TextView getTextView(boolean isFour) {
        TextView textView = new TextView(getContext());
        textView.setId(R.id.mine_tv_name);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, 5, 0, 5);
        if (isFour) {
            textView.setLayoutParams(tv_params);
        } else {
            textView.setLayoutParams(tv_params_wrap);
        }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        setTextColor(false, textView, null);
        return textView;
    }

    public void setViewPager(final ViewPager viewPager) {
        if (viewPager == null) {
            return;
        }
        removeAllViews();

        childCount = viewPager.getAdapter().getCount();
        setWeightSum(childCount);
        for (int i = 0; i < childCount; i++) {
            TextView textView = null;
            LinearLayout linearLayout = null;
            linearLayout = getLL();
            textView = getTextView(true);
            if (listData.size() == 0) {
                if (childCount == 3) {
                    setThreeText(i, textView);
                } else {
                    setText(i, textView);
                }
            } else {
                if (listData.size() != childCount) {
                    ToastUtil.showInfo(getContext(), "list数据与viewpage个数不对等!");
                    return;
                }
                setListDataText(i, textView);
            }

            if (i == 0) {
                setTextColor(true, textView, linearLayout);
            }
            linearLayout.addView(textView);
            addView(linearLayout);
            final int finalI = i;
            linearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(finalI);
                }
            });
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (getChildCount() > position) {
                    int childCount = getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View view = getChildAt(i);
                        TextView textView = (TextView) view.findViewById(R.id.mine_tv_name);
                        LinearLayout mine_ll_total = (LinearLayout) view.findViewById(R.id.mine_ll_total);
                        if (textView != null) {
                            if (i == position) {
                                setTextColor(true, textView, mine_ll_total);
                            } else {
                                setTextColor(false, textView, mine_ll_total);
                            }
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getChildAt(viewPager.getCurrentItem()).setPressed(true);
    }

    /**
     * 是否选中.
     *
     * @param pressed
     * @param textView
     */
    private void setTextColor(boolean pressed, TextView textView, LinearLayout mine_ll_total) {
        if (pressed) {
            if (mine_ll_total != null) {
//                mine_ll_total.setBackgroundResource(R.drawable.mine_bg_3_small);
                if (childCount == 2) {
                    mine_ll_total.setBackgroundResource(R.drawable.mine_bg_4_small);
                } else if (childCount == 3) {
                    mine_ll_total.setBackgroundResource(R.drawable.mine_bg_4_small);
                } else {
                    mine_ll_total.setBackgroundResource(R.drawable.mine_bg_4_small);
                }
            }
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        } else {
            if (mine_ll_total != null) {
                if (mine_ll_total != null) {
                    mine_ll_total.setBackgroundResource(R.drawable.minepoint_bg_border);
                    textView.setBackgroundResource(R.drawable.minepoint_bg_border);
                }
            }
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.color_afafaf));
        }
    }
}
