package com.app.merbng.mycodelibs.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ght on 2017/2/18.
 */

@SuppressWarnings("CheckStyle")
public class TopicBean  implements Parcelable {
    private String topicId; // 新闻ID
    private String title; //标题
    private int conType = TOPIC_TEXT; //内容类型0文字1图片2链接
    private String content; //内容
    private String linkUrl;//链接地址
    private String imgsUrl;//json字符串形式的数组 需要转化为urls字段使用
    private double conScore = 0.0d; //争议得分
    private String createdAt;//创建时间
    private int downNum = 0; //反对票的数量
    private double hotScore = 0;//热度评分
    private boolean mySave = false;
    private boolean myRead = false;

    private String space;//用户自定义的位置信息
    private String userId; //创建者
    private String nickName;//姓名
    private String img; //创建者头像
    private String imgSize; //单图时候返回图片的大小
    private int commentNum = 0; //总评论数(包含子评论)
    private int upNum = 0; //赞成票的数量
    private int state = 0; //评论状态0正常1删除
    private int type; //是否是令人不适需要过滤的
    private String communityId;//社区id
    private String communityName;//社区名称
    private CharSequence contentSpan;
    /*
     * 转换字段 并不是后台返回的字段.
     */
    private List<String> urls = new ArrayList<>();

    /*
     * 常量部分 话题类型
     */
    public static final int TOPIC_TEXT = 0;
    public static final int TOPIC_IMG = 1;
    public static final int TOPIC_LINK = 2;

    public TopicBean() {
    }


    protected TopicBean(Parcel in) {
        topicId = in.readString();
        title = in.readString();
        conType = in.readInt();
        content = in.readString();
        linkUrl = in.readString();
        imgsUrl = in.readString();
        conScore = in.readDouble();
        createdAt = in.readString();
        downNum = in.readInt();
        hotScore = in.readDouble();
        mySave = in.readByte() != 0;
        myRead = in.readByte() != 0;
        space = in.readString();
        userId = in.readString();
        nickName = in.readString();
        img = in.readString();
        imgSize = in.readString();
        commentNum = in.readInt();
        upNum = in.readInt();
        state = in.readInt();
        type = in.readInt();
        communityId = in.readString();
        communityName = in.readString();
        urls = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topicId);
        dest.writeString(title);
        dest.writeInt(conType);
        dest.writeString(content);
        dest.writeString(linkUrl);
        dest.writeString(imgsUrl);
        dest.writeDouble(conScore);
        dest.writeString(createdAt);
        dest.writeInt(downNum);
        dest.writeDouble(hotScore);
        dest.writeByte((byte) (mySave ? 1 : 0));
        dest.writeByte((byte) (myRead ? 1 : 0));
        dest.writeString(space);
        dest.writeString(userId);
        dest.writeString(nickName);
        dest.writeString(img);
        dest.writeString(imgSize);
        dest.writeInt(commentNum);
        dest.writeInt(upNum);
        dest.writeInt(state);
        dest.writeInt(type);
        dest.writeString(communityId);
        dest.writeString(communityName);
        dest.writeStringList(urls);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TopicBean> CREATOR = new Creator<TopicBean>() {
        @Override
        public TopicBean createFromParcel(Parcel in) {
            return new TopicBean(in);
        }

        @Override
        public TopicBean[] newArray(int size) {
            return new TopicBean[size];
        }
    };

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public List<String> getUrls() {
        if (conType != TOPIC_TEXT && (urls == null || urls.size() == 0) && !TextUtils.isEmpty(imgsUrl)) {
            Gson gson = new Gson();
            try {
                urls = gson.fromJson(imgsUrl, new TypeToken<ArrayList<String>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }


    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public double getConScore() {
        return conScore;
    }

    public void setConScore(double conScore) {
        this.conScore = conScore;
    }

    public int getConType() {
        return conType;
    }

    public void setConType(int conType) {
        this.conType = conType;
    }

    public int getDownNum() {
        return downNum;
    }

    public void setDownNum(int downNum) {
        this.downNum = downNum;
    }

    public int getUpNum() {
        return upNum;
    }

    public void setUpNum(int upNum) {
        this.upNum = upNum;
    }

    public double getHotScore() {
        return hotScore;
    }

    public void setHotScore(double hotScore) {
        this.hotScore = hotScore;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMySave() {
        return mySave;
    }

    public void setMySave(boolean mySave) {
        this.mySave = mySave;
    }

    public boolean isMyRead() {
        return myRead;
    }

    public void setMyRead(boolean myRead) {
        this.myRead = myRead;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImgsUrl() {
        return imgsUrl;
    }

    public void setImgsUrl(String imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getImgSize() {
        return imgSize;
    }

    public void setImgSize(String imgSize) {
        this.imgSize = imgSize;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public CharSequence getContentSpan() {
        return contentSpan;
    }

    public void setContentSpan(CharSequence contentSpan) {
        this.contentSpan = contentSpan;
    }


}
