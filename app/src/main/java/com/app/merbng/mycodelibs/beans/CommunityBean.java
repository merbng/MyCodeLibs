package com.app.merbng.mycodelibs.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 社区bean
 * Created by ght on 2017/2/18.
 */

@SuppressWarnings("CheckStyle")
public class CommunityBean  implements Parcelable {
    private boolean isMostNew;
    private String communityId; // 新闻ID
    private String name; //社区名字
    /**
     * 社区的0;表示可用 1:表示删除
     */
    private int state;

    /**
     * 排序字段.从大到小
     */
    private int sort;

    /**
     * 关于社区的描述内容
     */
    private String detail;

    /**
     * 封面图片
     */
    private String coverImg;

    /**
     * 社区的规则
     */
    private String rule;

    /**
     * 关注的人数
     */
    private int followNum;

    /**
     * 创建用户的id
     */
    private String userId;

    /**
     * 创建时间
     */
    private String createdAt;

    private int isJoin; //-1:标识该用户尚未关注该社区 0:正常关注 1:绑定社区 2:社区的黑名单 3:表示社区的管理员

    public int getTopNum() {
        return topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

    private int topNum;


    public CommunityBean() {
    }
    public int getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(int isJoin) {
        this.isJoin = isJoin;
    }

    public String getDescription() {
        return detail;
    }

    public void setDescription(String detail) {
        this.detail = detail;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return name;
    }

    public void setCommunityName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }


    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isMostNew ? (byte) 1 : (byte) 0);
        dest.writeString(this.communityId);
        dest.writeString(this.name);
        dest.writeInt(this.state);
        dest.writeInt(this.sort);
        dest.writeString(this.detail);
        dest.writeString(this.coverImg);
        dest.writeString(this.rule);
        dest.writeInt(this.followNum);
        dest.writeString(this.userId);
        dest.writeString(this.createdAt);
        dest.writeInt(this.isJoin);
        dest.writeInt(this.topNum);
    }

    protected CommunityBean(Parcel in) {
        this.isMostNew = in.readByte() != 0;
        this.communityId = in.readString();
        this.name = in.readString();
        this.state = in.readInt();
        this.sort = in.readInt();
        this.detail = in.readString();
        this.coverImg = in.readString();
        this.rule = in.readString();
        this.followNum = in.readInt();
        this.userId = in.readString();
        this.createdAt = in.readString();
        this.isJoin = in.readInt();
        this.topNum = in.readInt();
    }

    public static final Creator<CommunityBean> CREATOR = new Creator<CommunityBean>() {
        @Override
        public CommunityBean createFromParcel(Parcel source) {
            return new CommunityBean(source);
        }

        @Override
        public CommunityBean[] newArray(int size) {
            return new CommunityBean[size];
        }
    };

    public boolean isMostNew() {
        return isMostNew;
    }

    public void setMostNew(boolean mostNew) {
        isMostNew = mostNew;
    }
}

