package com.spoon.app.jsbridge_se.core.extension.bean;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-04-02 17:51
 * description :
 */
public class Event implements Serializable {
    public String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
