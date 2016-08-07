package com.app.merbng.mycodelibs.A_galleryview;

import android.os.Bundle;
import android.view.View;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿 【即刻】 app  首页滚动效果
 * https://github.com/JeasonWong/JikeGallery
 */
public class GalleryActivity extends BaseActivity {

    private List<GalleryView> mGalleryList = new ArrayList<>();
    private List<GalleryEntity> mEntities = new ArrayList<>();

    private String[] mImgs = new String[]{
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2804627377,428605343&fm=116&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4035587974,830728327&fm=116&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1487828992,450495111&fm=116&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2162493458,4173326614&fm=116&gp=0.jpg"};

    private String[] mTitles = new String[]{
            "这是一个简单骷髅头",
            "骷髅头",
            "骷髅",
            "骷髅头头头头头头"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mGalleryList.add((GalleryView) findViewById(R.id.gallery0));
        mGalleryList.add((GalleryView) findViewById(R.id.gallery1));
        mGalleryList.add((GalleryView) findViewById(R.id.gallery2));

        for (int i = 0; i < mGalleryList.size(); i++) {
            mEntities.clear();
            for (int j = 0; j < mImgs.length; j++) {
                GalleryEntity entity = new GalleryEntity();
                entity.imgUrl = mImgs[j];
                entity.title = mTitles[j];
                mEntities.add(entity);
            }
            mGalleryList.get(i).addGalleryData(mEntities);
        }

    }

    private void startSmooth() {
        for (int i = 0; i < mGalleryList.size(); i++) {
            final int index = i;
            mGalleryList.get(i).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mGalleryList.get(index).startSmooth();
                }
            }, 100 * i);
        }

    }

    public void onRefresh(View view) {
        startSmooth();
    }

}
