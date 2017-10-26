package com.app.netconnection;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by merbng on 2017/10/26.
 */

public class ErrorModel implements Parcelable {
    private String resultcode;
    private String error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resultcode);
        dest.writeString(this.error_code);
    }

    public ErrorModel() {
    }

    protected ErrorModel(Parcel in) {
        this.resultcode = in.readString();
        this.error_code = in.readString();
    }

    public static final Parcelable.Creator<ErrorModel> CREATOR = new Parcelable.Creator<ErrorModel>() {
        @Override
        public ErrorModel createFromParcel(Parcel source) {
            return new ErrorModel(source);
        }

        @Override
        public ErrorModel[] newArray(int size) {
            return new ErrorModel[size];
        }
    };
}
