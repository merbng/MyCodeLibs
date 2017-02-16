package com.example.studymvp.datamodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studymvp.MainActivity;

/**
 * Created by Merbng on 2017/2/16.
 */

public class IGetStringIml implements IGetString {
    Context mContext;

    @Override
    public String getName() {
        String name;
        SharedPreferences sharedPreferences;
         sharedPreferences = mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
       name = sharedPreferences.getString("name","");
        return name;
    }

    @Override
    public void saveName(String name) {
        SharedPreferences sharedPreferences = null;
        mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("name", name);
        edit.commit();
    }
}
