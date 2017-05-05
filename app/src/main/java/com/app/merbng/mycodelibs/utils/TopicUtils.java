package com.app.merbng.mycodelibs.utils;

import android.text.TextUtils;

import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.beans.CommunityBean;
import com.app.merbng.mycodelibs.beans.TopicBean;
import com.app.merbng.mycodelibs.model.ShareBean;


/**
 * Created by ght on 2017/2/28.
 * 话题工具类
 */

public final class TopicUtils {
    /**
     * 分享话题.
     *
     * @param topicBean 话题
     * @param platType  分享类型0:微信 1:微信朋友圈 2:表示QQ  3 qq空间   4:微博
     * @return
     */
    public static ShareBean topicBean2ShareBean(TopicBean topicBean, int platType) {
        ShareBean shareBean = new ShareBean();
        shareBean.setUserId(topicBean.getUserId());
        shareBean.setTargetId(topicBean.getTopicId());
        shareBean.setNickName(topicBean.getNickName());
        shareBean.setMySave(topicBean.isMySave());
        if (topicBean.getUrls() != null && topicBean.getUrls().size() > 0) {
            shareBean.setCoverUrl(topicBean.getUrls().get(0));
        } else {
            shareBean.setCoverUrl(Constent.LOGO_URL);
        }
//        内容类型0文字1图片2链接
        int shareType = topicBean.getConType();
        String topicTitle = topicBean.getTitle();
        String content = "";
        if (topicBean.getContent() != null) {
            content = topicBean.getContent().trim().replace("\n", "");
        }
        String introduction = "";//分享的描述
        String sharTitle = "";//分享的标题
        if (topicBean.getType() == ShareBean.TYPE_SHARE_TOPIC) {
            sharTitle = "大米社区——尽情分享，自由表达 ";
            if (topicTitle == null) {
                switch (shareType) {
                    case 0://文字
                        if (content.length() < 21) {
                            introduction = content;
                        } else {
                            introduction = content.substring(0, 21);
                        }
                        break;
                    case 1://图片
                        if (TextUtils.isEmpty(content)) {
                            introduction = "分享图片";
                        } else {
                            introduction = content;
                        }
                        break;
                    case 2://链接
                        if (content != null) {
                            introduction = content;
                        } else {
                            introduction = "分享链接";
                        }
                        break;
                }
            } else {//标题不为空
                switch (shareType) {
                    case 2://链接
                        introduction = topicTitle;
                        break;
                }
            }
            shareBean.setType(ShareBean.TYPE_SHARE_TOPIC);
        } else {
            introduction = topicBean.getContent();
            sharTitle = topicBean.getTitle();
            shareBean.setType(ShareBean.TYPE_SHARE_APP);
        }
//当分享到微信|朋友圈的时候 title 为 introduction
        if (platType==1) {
            shareBean.setTitle(introduction);
        }else {
            shareBean.setTitle(sharTitle);
        }
        shareBean.setIntroduction(introduction);
        return shareBean;
    }
    /**
     * 分享社区.
     *
     * @param communityBean 社区
     * @return
     */
    public static ShareBean communityBean2ShareBean(CommunityBean communityBean) {
        ShareBean shareBean = new ShareBean();
        shareBean.setUserId("");
        shareBean.setTargetId(communityBean.getCommunityId());
        shareBean.setNickName(communityBean.getCommunityName());
        if (communityBean.getCoverImg() != null) {
            shareBean.setCoverUrl(communityBean.getCoverImg());
        } else {
            shareBean.setCoverUrl(Constent.LOGO_URL);
        }
        String communityName = communityBean.getCommunityName();
        String introduction =communityBean.getDescription();
        shareBean.setType(ShareBean.TYPE_SHARE_COMMUNITY);
        shareBean.setTitle(communityName);
        shareBean.setIntroduction(introduction);
        return shareBean;
    }

    /**
     * 隐藏构造器
     */
    private TopicUtils() {
    }

    public static final int TYPE_QQ = 1;
    public static final int TYPE_WEIXIN = 2;
    public static final int TYPE_FRIENDCIRCLE = 3;
    public static final int TYPE_SINA = 4;
    public static final int TYPE_QZONE = 5;



    public static String extractMp4Url(TopicBean upTopic) {
//        if (!TextUtils.isEmpty(upTopic.getLinkUrl())) {
//            if (StringUtil.getUrl(upTopic.getLinkUrl()).endsWith(".mp4")) {
//                return upTopic.getLinkUrl();
//            }
//        }

        return upTopic.getImg();
    }
}
