package com.ospoon.app.sunlife.jsbridge_plugins_se.request;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-03-18 23:16
 * description :
 */
public class ToastJsRequest implements Serializable {
    private String text;
    private int duration;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
