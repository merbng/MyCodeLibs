package com.app.merbng.mycodelibs.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.merbng.mycodelibs.base.BaseBean;


/**
 * Created by taojunbin on 2016/8/2.
 * 业务错误的实体类
 */
public class ErrorModel extends BaseBean implements Parcelable {
    /**
     * 与后台约定的错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;

    public ErrorModel() {
    }

    protected ErrorModel(Parcel in) {
        message = in.readString();
        code = in.readInt();
    }

    public static final Creator<ErrorModel> CREATOR = new Creator<ErrorModel>() {
        @Override
        public ErrorModel createFromParcel(Parcel in) {
            return new ErrorModel(in);
        }

        @Override
        public ErrorModel[] newArray(int size) {
            return new ErrorModel[size];
        }
    };

    public String getMessage() {
        if (message == null || message.length() == 0) {
            message = ErrorMessage.getErrorMessage(code);
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeInt(code);
    }
}
