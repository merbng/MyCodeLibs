package com.app.merbng.mycodelibs.model;

import java.io.Serializable;

/**
 * Created by Merbng on 2016/12/27.
 */

public class ShareBean implements Serializable {
    public static final int TYPE_SHARE_TOPIC = 0; //分享帖子
    public static final int TYPE_SHARE_APP = 1; //分享APP

    public static final int TYPE_SHARE_COMMUNITY = 2; //分享APP
    private int sourceIdType;
    private String title;
    private String coverUrl;
    private String targetId;//这个可以当任意id
    private String userId;//这个只能是用户id
    private String nickName;//
    private boolean mySave = false;

    public boolean isMySave() {
        return mySave;
    }

    public void setMySave(boolean mySave) {
        this.mySave = mySave;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSourceIdType() {
        return sourceIdType;
    }

    public void setSourceIdType(int sourceIdType) {
        this.sourceIdType = sourceIdType;
    }

    public String getIntroduction() {
        if (introduction == null || introduction.isEmpty()) {
            introduction = "";
        }
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    private String introduction;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public String toString() {
        return "ShareBean{" +
                "sourceIdType=" + sourceIdType +
                ", title='" + title + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", targetId='" + targetId + '\'' +
                ", userId='" + userId + '\'' +
                ", introduction='" + introduction + '\'' +
                ", type=" + type +
                '}';
    }
}
