package com.app.merbng.mycodelibs.A_privateTest;

/**
 * Created by Merbng on 2017/4/18.
 */

public class Father {
    protected String firstName = "平";
    /*default*/ String address = "北京市";
    protected String lastName = "张";
    public String phone = "13366668888";

    @Override
    public String toString() {
        return "Father [firstName=" + firstName + ", address=" + address + ", lastName=" + lastName + ", phone=" + phone
                + "]";
    }
}
