package com.spoon.app.jsbridge_n22.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import java.util.Map;


/**
 * 存储
 *
 * @author gdk
 */
public class SPUtils {
    private static SharedPreferences sharedPreferences;

    public static void init(Application application) {
        sharedPreferences = application.getSharedPreferences("when", Context.MODE_PRIVATE);
    }


    public static void clear(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    //存String
    public static void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    //取String
    public static String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    //取String
    public static String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    //存储boolean
    public static void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    //取boolean
    public static boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    //存int
    public static void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    //取int
    public static int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    //存long
    public static void putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    //取long
    public static long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    //存float
    public static void putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    //取float
    public static float getFloat(String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    /**
     * 用于保存集合
     *
     * @param key key
     * @param map map数据
     * @return 保存结果
     */
    public static <V> boolean putHashMapData(String key, Map<String, V> map) {
        boolean result;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            String json = GsonUtils.mapToJson(map);
            editor.putString(key, json);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 用于保存集合
     *
     * @param key key
     * @return 保存结果
     */
    public static <V> Map<String, V> getHashMapData(String key) {
        String res = sharedPreferences.getString(key, "");
        return GsonUtils.parseJsonToMap(res);
    }
}
