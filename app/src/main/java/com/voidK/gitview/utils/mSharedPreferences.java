package com.voidK.gitview.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class mSharedPreferences {
    private final Context context;

    public mSharedPreferences(Context context) {
        this.context = context;
    }

    public void setToken(String token){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(Constants.TOKEN_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("token", token);
        mEditor.apply();
    }

    public String getToken(){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(Constants.TOKEN_TAG, Context.MODE_PRIVATE);
        return mSharedPreferences.getString("token", "");
    }

    public void setSortItem(String sortItem){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(Constants.SORT_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("sort_item", sortItem);
        mEditor.apply();
    }

    public String getSortItem(){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(Constants.SORT_TAG, Context.MODE_PRIVATE);
        return mSharedPreferences.getString("sort_item", "");
    }

}
