package com.app.merbng.mycodelibs.A_SharedBottomSheetDialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.PackageManager.GET_SHARED_LIBRARY_FILES;

/**
 * Created by ght on 2016/10/9.
 * 默认的分享方式有
 * com.tencent.mobileqq
 * com.sina.weibo
 * com.tencent.mm
 */

public class SharedBottomSheetDialog {
    private Builder builder;
    private BottomSheetDialog bottomSheetDialog;
    private List<AppInfoBean> sdkSharedList = new ArrayList<>();
    private final String QQPackageName = "com.tencent.mobileqq";
    private final String SinaPackageName = "com.sina.weibo";
    private final String WechatPackageName = "com.tencent.mm";
    private final String WechatMomentPackageName = "com.tencent.Moments";//假的，实际没有这个报名
    private final String QZonePackageName = "com.qzone";


    class AppInfoBean {
        String appName;
        Drawable appIcon;
        String packageName;
        String needStartAcivityClassName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AppInfoBean)) return false;
            AppInfoBean that = (AppInfoBean) o;
            return (packageName == that.packageName) || (packageName != null && packageName.equals(that.packageName));
        }

        @Override
        public int hashCode() {
            return packageName == null ? 0 : packageName.hashCode();
        }

        public AppInfoBean(ResolveInfo info, PackageManager packageManager) {
            appIcon = info.loadIcon(packageManager);
            packageName = info.activityInfo.packageName;
            appName = info.loadLabel(packageManager).toString();
            needStartAcivityClassName = info.activityInfo.name;
        }

        public AppInfoBean(String packageName, String appName, PackageManager packageManager) {

            this.packageName = packageName;
            try {
                if (WechatMomentPackageName.equals(packageName)) {
                    appIcon = builder.context.getResources().getDrawable(R.drawable.skyblue_logo_wechatmoments_checked);
                } else {
                    appIcon = packageManager.getApplicationIcon(packageName);
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            this.appName = appName;

        }
    }

    public SharedBottomSheetDialog(Builder builder) {
        this.builder = builder;
        Context context = builder.context;
        bottomSheetDialog = new BottomSheetDialog(context);
        LayoutInflater inflater = bottomSheetDialog.getLayoutInflater();
        List<AppInfoBean> appInfoList = getShareInfos(context, getShareApps(context));
        View mView = inflater.inflate(R.layout.dialog_share_bottom_sheet, null);
        TextView recentSharedTitle = (TextView) mView.findViewById(R.id.recent_shared_title);
        LinearLayout recentSharedLayout = (LinearLayout) mView.findViewById(R.id.recent_shared_layout);
        RecyclerView sharedList = (RecyclerView) mView.findViewById(R.id.shared_list);
        sharedList.setLayoutManager(new LinearLayoutManager(context));
        sharedList.setAdapter(new SharedListAdapter(appInfoList));

        String recentPackageName = SharedPrefUtils.getRecentSharedPackage(context);
        if (TextUtils.isEmpty(recentPackageName)) {
            recentSharedTitle.setVisibility(View.GONE);
            recentSharedLayout.setVisibility(View.GONE);
        } else {
            PackageManager pManager = context.getPackageManager();
            try {
                ((ImageView) mView.findViewById(R.id.recent_shared_logo)).setImageDrawable(pManager.getApplicationIcon(recentPackageName));
                ((TextView) mView.findViewById(R.id.recent_shared_label)).setText(pManager.getApplicationLabel(pManager.getApplicationInfo(recentPackageName, GET_SHARED_LIBRARY_FILES)));
                AppInfoBean appInfo = getAppInfoBeanFromPackageName(recentPackageName, context.getPackageManager());
                if (appInfo != null) {
                    recentSharedLayout.setTag(appInfo);
                } else {
                    for (int i = 0; i < appInfoList.size(); i++) {
                        if (appInfoList.get(i).packageName.equals(recentPackageName)) {
                            recentSharedLayout.setTag(appInfoList.get(i));
                        }
                    }

                }

                recentSharedLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onShareItemClick((AppInfoBean) v.getTag());
                    }
                });
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                recentSharedTitle.setVisibility(View.GONE);
                recentSharedLayout.setVisibility(View.GONE);
            }

        }

        bottomSheetDialog.setContentView(mView);
        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                bottomSheetDialog = null;
            }
        });
    }

    public static class Builder {
        private String title;
        private String content;
        private String imgUrl;
        private Context context;
        private String url;

        public Builder(@NonNull Context context) {
            this.context = context;

        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public SharedBottomSheetDialog build() {

            return new SharedBottomSheetDialog(this);
        }

        public SharedBottomSheetDialog show() {
            SharedBottomSheetDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }

    public void show() {
        bottomSheetDialog.show();
    }

    public List<ResolveInfo> getShareApps(Context context) {
        List<ResolveInfo> mApps = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
//      intent.setType("*/*");
        PackageManager pManager = context.getPackageManager();
        mApps = pManager.queryIntentActivities(intent,
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        return mApps;
    }

    private AppInfoBean getAppInfoBeanFromPackageName(String packageName, PackageManager pManager) {
        if (QQPackageName.equals(packageName)) {
            return new AppInfoBean(QQPackageName, "QQ好友", pManager);
        } else if (SinaPackageName.equals(packageName)) {
            return new AppInfoBean(SinaPackageName, "新浪微博", pManager);
        } else if (WechatPackageName.equals(packageName)) {
            return new AppInfoBean(WechatPackageName, "微信好友", pManager);
        } else if (QZonePackageName.equals(packageName)) {
            return new AppInfoBean(QZonePackageName, "QQ空间", pManager);
        } else if (WechatMomentPackageName.equals(packageName)) {
            return new AppInfoBean(WechatMomentPackageName, "微信朋友圈", pManager);
        } else {
            return null;
        }

    }

    public List<AppInfoBean> getShareInfos(Context context, List<ResolveInfo> resolveInfos) {
        PackageManager pManager = context.getPackageManager();
        List<AppInfoBean> list = new ArrayList<>();

        for (int i = 0; i < resolveInfos.size(); i++) {
            String packageName = resolveInfos.get(i).activityInfo.packageName;
            AppInfoBean appInfoBean = getAppInfoBeanFromPackageName(packageName, pManager);
            if (appInfoBean != null) {
                if (!sdkSharedList.contains(appInfoBean)) {
                    sdkSharedList.add(appInfoBean);
                    if (WechatPackageName.equals(packageName)) {
                        sdkSharedList.add(new AppInfoBean(WechatMomentPackageName, "微信朋友圈", pManager));
                    }
                }
            } else {
                list.add(new AppInfoBean(resolveInfos.get(i), pManager));
            }
        }
        return list;
    }

    private class SharedListAdapter extends RecyclerView.Adapter<SharedItemViewHolder> {
        List<AppInfoBean> appInfoList = new ArrayList<>();

        public SharedListAdapter(List<AppInfoBean> appInfoList) {
            this.appInfoList = appInfoList;
        }

        @Override
        public SharedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SharedItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_share, parent, false));
        }

        @Override
        public void onBindViewHolder(SharedItemViewHolder holder, int position) {
            AppInfoBean appInfoBean;
            if (position < sdkSharedList.size()) {
                appInfoBean = sdkSharedList.get(position);
            } else {
                appInfoBean = appInfoList.get(position - sdkSharedList.size());
            }
            holder.itemView.setTag(appInfoBean);
            holder.icon.setImageDrawable(appInfoBean.appIcon);
            holder.label.setText(appInfoBean.appName);

        }

        @Override
        public int getItemCount() {
            return sdkSharedList.size() + appInfoList.size();
        }
    }

    public void onShareItemClick(AppInfoBean appInfo) {
        if (appInfo != null) {

            String packageName = appInfo.packageName;

            SharedPrefUtils.putRecentSharedPackage(builder.context,packageName);
            if (QQPackageName.equals(packageName)) {
//                QQ.ShareParams params = new QQ.ShareParams();
//                params.setTitle(builder.title);
//                params.setText(builder.title);
//                params.setImageUrl(builder.imgUrl);
//                params.setTitleUrl(builder.url);
//                ShareAPI.shareToQQ(builder.context, params, null);
            } else if (SinaPackageName.equals(packageName)) {
//                SinaWeibo.ShareParams params = new SinaWeibo.ShareParams();
//                params.setTitle(builder.title);
//                params.setText(builder.title);
//                            params.setImageUrl(builder.imgUrl);

//                ShareAPI.shareToSina(builder.context, params, null);
            } else if (WechatPackageName.equals(packageName)) {

//                Wechat.ShareParams params = new Wechat.ShareParams();
//                params.setTitle(builder.title);
//                params.setText(builder.title);
//                params.setImageUrl(builder.imgUrl);
//                params.setUrl(builder.url);
//                ShareAPI.shareToWeiXin(builder.context, params, null);

            } else if (WechatMomentPackageName.equals(packageName)) {
//                WechatMoments.ShareParams params = new WechatMoments.ShareParams();
//                params.setTitle(builder.title);
//                params.setText(builder.title);
//                params.setImageUrl(builder.imgUrl);
//                params.setUrl(builder.url);
//                ShareAPI.shareToWechatMoments(builder.context, params, null);
            } else if (QZonePackageName.equals(packageName)) {
//                QZone.ShareParams params = new QZone.ShareParams();
//                params.setTitle(builder.title);
//                params.setText(builder.title);
//                params.setTitleUrl(builder.url);
//                params.setImageUrl(builder.imgUrl);
//                params.setSite("UP社区");
//                params.setSiteUrl("");
//                ShareAPI.shareToQZone(builder.context, params, null);
            } else {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setComponent(new ComponentName(appInfo.packageName, appInfo.needStartAcivityClassName));

                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, builder.url);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                builder.context.startActivity(shareIntent);
            }

        }
    }

    private class SharedItemViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView label;

        public SharedItemViewHolder(final View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.recent_shared_logo);
            label = (TextView) itemView.findViewById(R.id.recent_shared_label);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onShareItemClick((AppInfoBean) v.getTag());
                }
            });
        }
    }

}
