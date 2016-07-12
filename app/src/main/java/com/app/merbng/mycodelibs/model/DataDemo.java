package com.app.merbng.mycodelibs.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zx on 2016/7/12.
 */
public class DataDemo implements Parcelable {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public DataDemo(String name) {
        this.name = name;
    }

    public DataDemo() {
    }

    protected DataDemo(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<DataDemo> CREATOR = new Creator<DataDemo>() {
        public DataDemo createFromParcel(Parcel source) {
            return new DataDemo(source);
        }

        public DataDemo[] newArray(int size) {
            return new DataDemo[size];
        }
    };
}
