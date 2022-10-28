package com.ospoon.app.sunlife.jsbridge_plugins_se.request;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-03-24 14:40
 * description :
 */
public class SharedPreferencesJsRequest implements Serializable {

    private int mode;

    private String key;

    private String value;

    public SharedPreferencesJsRequest() {
    }

    public SharedPreferencesJsRequest(int mode, String key) {
        this.mode = mode;
        this.key = key;
    }

    public SharedPreferencesJsRequest(int mode, String key, String value) {
        this.mode = mode;
        this.key = key;
        this.value = value;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
