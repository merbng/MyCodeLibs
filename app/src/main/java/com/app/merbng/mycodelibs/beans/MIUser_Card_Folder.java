package com.app.merbng.mycodelibs.beans;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

/**
 * 卡片的bean
 */
public class MIUser_Card_Folder implements Serializable {
    //  用户卡片ID
    private String userCardId;
    //  位置
    private String locationName;
    //  经度
    private float longitude;
    //  维度
    private float latitude;
    //  置顶时间
    private String topTime;
    //  专辑ID
    private String folderId;
    //  卡片ID
    private String cardId;
    //  评论数量
    private int commentCnt;
    //  影藏标志位
    private int hideFlag;
    //  用户ID
    private String userId;
    //  移动卡片的时间
    private String moveSaveCardTime;
    //  删除标志位
    private int delFlag;
    //  创建
    private String createdAt;
    //  更新
    private String updatedAt;
    //  昵称
    private String nickName;
    //  用户头像
    private String userImgUrl;
    //  卡片类型
    private int cardType;
    //  卡片标题
    private String title;
    private int titleFlag;
    //  点赞数量
    private int likeCnt;
    //  收藏数量
    private int collectCnt;
    //  举报次数
    private int reportCnt;
    //  订阅量
    private int subscribeCnt;
    //  卡片封面
    private String cardCoverUrl;
    //  卡片长度
    private int width;
    //  卡片高度
    private int height;
    //  主色
    private int colorArt;
    //  专辑名称
    private String folderName;
    //  专辑封面
    private String folderCoverUrl;
    //  比重
    private String searchWeight;
    //  标签的ID(收藏夹详情专用)
    private String tagId;
    //  收藏夹名称
    private String tableName;
    //  最后一次在本地的更新时间
    private long lastUpDateInDb;
    //  当前用户ID
    private String currentUserId;
    //  用户类型
    private int userType;
    //////////数据库中的字段//////////
    /*瀑布流表字段*/
    public final static String UCF_ID = "userCardId";
    //  卡片地址名
    public final static String UCF_LOCATIONNAME = "locationName";
    //  经度
    public final static String UCF_LONGITUDE = "longitude";
    //  维度
    public final static String UCF_LATITUDE = "latitude";
    //  置顶时间
    public final static String UCF_TOPTIME = "topTime";
    //  收藏夹ID
    public final static String UCF_FOLDERID = "folderId";
    //  卡片ID
    public final static String UCF_CARDID = "cardId";
    //  评论数量
    public final static String UCF_COMMENTCNT = "commentCnt";
    //  卡片是否隐藏
    public final static String UCF_HIDEFLAG = "hideFlag";
    //  卡片收藏者ID
    public final static String UCF_USERID = "userId";
    //  移动卡片的时间
    public final static String UCF_MOVESAVECARDTIME = "moveSaveCardTime";
    //  是否删除
    public final static String UCF_DELFLAG = "delFlag";
    //  创建时间
    public final static String UCF_CREATEAT = "createdAt";
    //  更新时间
    public final static String UCF_UPDATEAT = "updatedAt";
    //  用户昵称
    public final static String UCF_NICKNAME = "nickName";
    //  用户头像
    public final static String UCF_USERIMGURL = "userImgUrl";
    //  卡片类型
    public final static String UCF_CARDTYPE = "cardType";
    //  卡片标题
    public final static String UCF_TITLE = "title";
    //  卡片标题标志位
    public final static String UCF_TITLEFLAG = "titleFlag";
    //  点赞次数
    public final static String UCF_LIKECNT = "likeCnt";
    //  收藏该卡片的人数
    public final static String UCF_COLLECTCNT = "collectCnt";
    //  举报该卡片的次数
    public final static String UCF_REPORTCNT = "reportCnt";
    //  卡片封面地址
    public final static String UCF_CARDCOVERURL = "cardCoverUrl";
    //  卡片封面主色调
    public final static String UCF_COLORART = "colorArt";
    //  卡片宽度
    public final static String UCF_WIDTH = "width";
    //  卡片高度
    public final static String UCF_HEIGHT = "height";
    //  卡片所在收藏夹名称
    public final static String UCF_FOLDERNAME = "folderName";
    //  卡片所在收藏夹封面
    public final static String UCF_FOLDERCOVERURL = "folderCoverUrl";
    //  插入数据库的时间
    public final static String UCF_INSERTTIME = "insertTime";
    //  当前用户ID
    public final static String UCF_CURRENTUSERID = "currentUserId";
    //  标签ID
    public final static String UCF_TAGID = "tagId";
    //  表名
    public final static String UCF_TABLENAME = "tableName";
    // 用户类型
    public final static String UCF_USERTYPE = "userType";

    //////////数据库中的字段//////////
    public MIUser_Card_Folder() {
    }

    public MIUser_Card_Folder(String userCardId, String locationName, float longitude, float latitude, String topTime, String folderId, String cardId, int commentCnt, int hideFlag, String userId, String moveSaveCardTime, int delFlag, String createdAt, String updatedAt, String nickName, String userImgUrl, int cardType, String title, int titleFlag, int likeCnt, int collectCnt, int reportCnt, String cardCoverUrl, int width, int height, int colorArt, String folderName, String folderCoverUrl, int userType) {
        this.userCardId = userCardId;
        this.locationName = locationName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.topTime = topTime;
        this.folderId = folderId;
        this.cardId = cardId;
        this.commentCnt = commentCnt;
        this.hideFlag = hideFlag;
        this.userId = userId;
        this.moveSaveCardTime = moveSaveCardTime;
        this.delFlag = delFlag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nickName = nickName;
        this.userImgUrl = userImgUrl;
        this.cardType = cardType;
        this.title = title;
        this.titleFlag = titleFlag;
        this.likeCnt = likeCnt;
        this.collectCnt = collectCnt;
        this.reportCnt = reportCnt;
        this.cardCoverUrl = cardCoverUrl;
        this.width = width;
        this.height = height;
        this.colorArt = colorArt;
        this.folderName = folderName;
        this.folderCoverUrl = folderCoverUrl;
        this.userType = userType;
    }

    /**
     * 0图文1链接2微博3链接分享4图文分5视6音7图文测试8链接测试
     **/
    public static final int TYPE_IMAGE_WORD = 0;
    public static final int TYPE_LINK = 1;
    public static final int TYPE_SINA = 2;
    public static final int TYPE_SHARE_LINK = 3;
    public static final int TYPE_SHARE_IMAGE_WORD = 4;
    public static final int TYPE_VIDEO = 5;
    public static final int TYPE_MUSIC = 6;
    public static final int TYPE_TEST_IMAGE_WORD = 7;
    public static final int TYPE_TEST_LINK = 8;

    public long getInsertTimeInDb() {
        return lastUpDateInDb;
    }

    public void setInsertTimeInDb(long lastUpDateInDb) {
        this.lastUpDateInDb = lastUpDateInDb;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getTopTime() {
        return topTime;
    }

    public void setTopTime(String topTime) {
        this.topTime = topTime;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    public int getSubscribeCnt() {
        return subscribeCnt;
    }

    public void setSubscribeCnt(int subscribeCnt) {
        this.subscribeCnt = subscribeCnt;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getHideFlag() {
        return hideFlag;
    }

    public void setHideFlag(int hideFlag) {
        this.hideFlag = hideFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoveSaveCardTime() {
        return moveSaveCardTime;
    }

    public void setMoveSaveCardTime(String moveSaveCardTime) {
        this.moveSaveCardTime = moveSaveCardTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getuserImgUrl() {
        return userImgUrl;
    }

    public void setuserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public int getType() {
        return cardType;
    }

    public void setType(int type) {
        this.cardType = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleFlag() {
        return titleFlag;
    }

    public void setTitleFlag(int titleFlag) {
        this.titleFlag = titleFlag;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getCollectCnt() {
        return collectCnt;
    }

    public void setCollectCnt(int collectCnt) {
        this.collectCnt = collectCnt;
    }

    public int getReportCnt() {
        return reportCnt;
    }

    public void setReportCnt(int reportCnt) {
        this.reportCnt = reportCnt;
    }

    public String getCardCover() {
        return cardCoverUrl;
    }

    public void setCardCover(String cardCover) {
        this.cardCoverUrl = cardCover;
    }

    public int getWidth() {
        return width;
    }

    public int getFormatHeight(int width_img) {
        if (cardCoverUrl == null || width == 0 || height == 0) {
            //如果没有封面,或者高度,宽度任一为零,
            return width_img;
        } else {
            float w = width;
            float h = height;
            float sw = width_img;
            float scall = 0;
            if (height != 0) {
                scall = w / h;
                if (scall <= 0.618f) {
                    return (int) (sw / 0.618f);
                } else {
                    return (int) (sw / scall);
                }
            } else {
                return width_img;
            }
        }
    }
 
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getColorArt() {
        return colorArt;
    }

    public void setColorArt(int colorArt) {
        this.colorArt = colorArt;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String name) {
        this.folderName = name;
    }

    public String getFolderCover() {
        return folderCoverUrl;
    }

    public void setFolderCover(String folderCover) {
        this.folderCoverUrl = folderCover;
    }

    public String getSearchWeight() {
        return searchWeight;
    }

    public void setSearchWeight(String searchWeight) {
        this.searchWeight = searchWeight;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "MIUser_Card_Folder{" +
                "userCardId='" + userCardId + '\'' +
                ", locationName='" + locationName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", topTime='" + topTime + '\'' +
                ", folderId='" + folderId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", commentCnt='" + commentCnt + '\'' +
                ", hideFlag='" + hideFlag + '\'' +
                ", userId='" + userId + '\'' +
                ", moveSaveCardTime='" + moveSaveCardTime + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userImgUrl='" + userImgUrl + '\'' +
                ", cardType='" + cardType + '\'' +
                ", title='" + title + '\'' +
                ", titleFlag='" + titleFlag + '\'' +
                ", likeCnt='" + likeCnt + '\'' +
                ", collectCnt='" + collectCnt + '\'' +
                ", reportCnt='" + reportCnt + '\'' +
                ", cardCoverUrl='" + cardCoverUrl + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", colorArt='" + colorArt + '\'' +
                ", name='" + folderName + '\'' +
                ", folderCoverUrl='" + folderCoverUrl + '\'' +
                '}';
    }

    /**
     * 从数据库取出值赋值给当前对象
     *
     * @param cursor
     */
    public void assignment(String tableName, Cursor cursor) {
        setTableName(tableName);
        setUserCardId(cursor.getString(cursor.getColumnIndex(UCF_ID)));
        setLocationName(cursor.getString(cursor.getColumnIndex(UCF_LOCATIONNAME)));
        setLongitude(cursor.getFloat(cursor.getColumnIndex(UCF_LONGITUDE)));
        setLatitude(cursor.getFloat(cursor.getColumnIndex(UCF_LATITUDE)));
        setTopTime(cursor.getString(cursor.getColumnIndex(UCF_TOPTIME)));
        setFolderId(cursor.getString(cursor.getColumnIndex(UCF_FOLDERID)));
        setCardId(cursor.getString(cursor.getColumnIndex(UCF_CARDID)));
        setCommentCnt(cursor.getInt(cursor.getColumnIndex(UCF_COMMENTCNT)));
        setHideFlag(cursor.getInt(cursor.getColumnIndex(UCF_HIDEFLAG)));
        setUserId(cursor.getString(cursor.getColumnIndex(UCF_USERID)));
        setMoveSaveCardTime(cursor.getString(cursor.getColumnIndex(UCF_MOVESAVECARDTIME)));
        setDelFlag(cursor.getInt(cursor.getColumnIndex(UCF_DELFLAG)));
        setCreatedAt(cursor.getString(cursor.getColumnIndex(UCF_CREATEAT)));
        setUpdatedAt(cursor.getString(cursor.getColumnIndex(UCF_UPDATEAT)));
        setNickName(cursor.getString(cursor.getColumnIndex(UCF_NICKNAME)));
        setuserImgUrl(cursor.getString(cursor.getColumnIndex(UCF_USERIMGURL)));
        setType(cursor.getInt(cursor.getColumnIndex(UCF_CARDTYPE)));
        setTitle(cursor.getString(cursor.getColumnIndex(UCF_TITLE)));
        setTitleFlag(cursor.getInt(cursor.getColumnIndex(UCF_TITLEFLAG)));
        setLikeCnt(cursor.getInt(cursor.getColumnIndex(UCF_LIKECNT)));
        setCollectCnt(cursor.getInt(cursor.getColumnIndex(UCF_COLLECTCNT)));
        setReportCnt(cursor.getInt(cursor.getColumnIndex(UCF_REPORTCNT)));
        setCardCover(cursor.getString(cursor.getColumnIndex(UCF_CARDCOVERURL)));
        setColorArt(cursor.getInt(cursor.getColumnIndex(UCF_COLORART)));
        setWidth(cursor.getInt(cursor.getColumnIndex(UCF_WIDTH)));
        setHeight(cursor.getInt(cursor.getColumnIndex(UCF_HEIGHT)));
        setFolderName(cursor.getString(cursor.getColumnIndex(UCF_FOLDERNAME)));
        setFolderCover(cursor.getString(cursor.getColumnIndex(UCF_FOLDERCOVERURL)));
        setInsertTimeInDb(cursor.getLong(cursor.getColumnIndex(UCF_INSERTTIME)));
        if (cursor.getColumnIndex(UCF_CURRENTUSERID) != -1) {
            setCurrentUserId(cursor.getString(cursor.getColumnIndex(UCF_CURRENTUSERID)));
        }
        if (cursor.getColumnIndex(UCF_TAGID) != -1) {
            setTagId(cursor.getString(cursor.getColumnIndex(UCF_TAGID)));
        }
    }

    /**
     * 将对象转为ContentValues存入数据库中
     *
     * @param bean
     * @return
     */
    public ContentValues assignment(String tableName, MIUser_Card_Folder bean, boolean hasTagId) {
        if (bean == null || bean.getUserId() == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(UCF_TABLENAME, tableName);
        contentValues.put(UCF_ID, bean.getUserCardId());
        contentValues.put(UCF_LOCATIONNAME, bean.getLocationName());
        contentValues.put(UCF_LONGITUDE, bean.getLongitude());
        contentValues.put(UCF_LATITUDE, bean.getLatitude());
        contentValues.put(UCF_TOPTIME, bean.getTopTime());
        contentValues.put(UCF_FOLDERID, bean.getFolderId());
        contentValues.put(UCF_CARDID, bean.getCardId());
        contentValues.put(UCF_COMMENTCNT, bean.getCommentCnt());
        contentValues.put(UCF_HIDEFLAG, bean.getHideFlag());
        contentValues.put(UCF_USERID, bean.getUserId());
        contentValues.put(UCF_MOVESAVECARDTIME, bean.getMoveSaveCardTime());
        contentValues.put(UCF_DELFLAG, bean.getDelFlag());
        contentValues.put(UCF_CREATEAT, bean.getCreatedAt());
        contentValues.put(UCF_UPDATEAT, bean.getUpdatedAt());
        contentValues.put(UCF_NICKNAME, bean.getNickName());
        contentValues.put(UCF_USERIMGURL, bean.getuserImgUrl());
        contentValues.put(UCF_CARDTYPE, bean.getType());
        contentValues.put(UCF_TITLE, bean.getTitle());
        contentValues.put(UCF_TITLEFLAG, bean.getTitleFlag());
        contentValues.put(UCF_LIKECNT, bean.getLikeCnt());
        contentValues.put(UCF_COLLECTCNT, bean.getCollectCnt());
        contentValues.put(UCF_REPORTCNT, bean.getReportCnt());
        contentValues.put(UCF_CARDCOVERURL, bean.getCardCover());
        contentValues.put(UCF_COLORART, bean.getColorArt());
        contentValues.put(UCF_WIDTH, bean.getWidth());
        contentValues.put(UCF_HEIGHT, bean.getHeight());
        contentValues.put(UCF_FOLDERNAME, bean.getFolderName());
        contentValues.put(UCF_FOLDERCOVERURL, bean.getFolderCover());
        contentValues.put(UCF_INSERTTIME, bean.getInsertTimeInDb());
        if (hasTagId) {
            contentValues.put(UCF_CURRENTUSERID, bean.getCurrentUserId());
            contentValues.put(UCF_TAGID, bean.getTagId());
        }
        return contentValues;
    }
}
