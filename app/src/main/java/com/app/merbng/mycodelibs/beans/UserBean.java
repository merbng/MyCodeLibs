package com.app.merbng.mycodelibs.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.merbng.mycodelibs.base.BaseBean;


/**
 * Created by ght on 2016/8/18.
 */
@SuppressWarnings("CheckStyle")
public class UserBean extends BaseBean implements Parcelable {
    public static final int USERTYPE_UNINVITATION = 0;
    public static final int USERTYPE_INVITATION = 1;

    public static final int SEX_MAN = 0;
    public static final int SEX_WOMAN = 1;
    public static final int SEX_NONO = 2;

    private String phone; //用户名
    private String userId; //用户ID
    private String nickName; //用户昵称
    private int userType; //用户类型
    private String deviceId;//
    private int sex;
    private int isAllow; //(0:表示允许别人查看,1表示不允许别人查看
    private String cookie;
    private String img;
    private String password;
    private String info;
    private int sourceId;
    private String createdAt;
    private double score = 0;
    private String signAt;
    private String invitationCode;//邀请码

    public UserBean() {
    }

    public int getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(int isAllow) {
        this.isAllow = isAllow;
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


    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSignAt() {
        return signAt;
    }

    public void setSignAt(String signAt) {
        this.signAt = signAt;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phone);
        dest.writeString(this.userId);
        dest.writeString(this.nickName);
        dest.writeInt(this.userType);
        dest.writeString(this.deviceId);
        dest.writeInt(this.sex);
        dest.writeInt(this.isAllow);
        dest.writeString(this.cookie);
        dest.writeString(this.img);
        dest.writeString(this.password);
        dest.writeString(this.info);
        dest.writeInt(this.sourceId);
        dest.writeString(this.createdAt);
        dest.writeDouble(this.score);
        dest.writeString(this.signAt);
        dest.writeString(this.invitationCode);
    }

    protected UserBean(Parcel in) {
        this.phone = in.readString();
        this.userId = in.readString();
        this.nickName = in.readString();
        this.userType = in.readInt();
        this.deviceId = in.readString();
        this.sex = in.readInt();
        this.isAllow = in.readInt();
        this.cookie = in.readString();
        this.img = in.readString();
        this.password = in.readString();
        this.info = in.readString();
        this.sourceId = in.readInt();
        this.createdAt = in.readString();
        this.score = in.readDouble();
        this.signAt = in.readString();
        this.invitationCode = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
