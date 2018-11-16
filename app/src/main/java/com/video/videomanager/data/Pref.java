package com.video.videomanager.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.video.videomanager.utils.Constants;


public class Pref {



    private Context mContext;


    public Pref(Context context) {

        this.mContext = context;
    }

    public String get(String key) {

        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        try {
            String value = settings.getString(key, null);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public String getString(String key, String defaultvalue) {

        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        try {
            String value = settings.getString(key, defaultvalue);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public int getInt(String key, int defaultvalue) {

        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        try {
            int value = settings.getInt(key, defaultvalue);
            return value;
        } catch (Exception e) {
            return 0;
        }
    }

    public long getLong(String key, long defaultValue) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        try {
            long value = settings.getLong(key, defaultValue);
            return value;
        } catch (Exception e) {
            return 0;
        }
    }




    public boolean getBoolean(String key) {

        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        try {
            boolean value = settings.getBoolean(key, false);
            return value;
        } catch (Exception e) {
            return false;
        }
    }

    public void put(String key, String value) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void putLong(String key, long value) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public boolean isContains(String key) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        if (settings.contains(key)) {
            return true;
        } else {
            return false;
        }

    }

    public void delete(String key) {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }

    public void deletePrefFile() {
        SharedPreferences settings = mContext.getSharedPreferences(
                Constants.SHARED_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }
}
