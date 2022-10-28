package com.spoon.app.jsbridge_se.core.extension.bean;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-04-02 17:52
 * description :
 */
public class BrowserMenu extends BrowserButton implements Serializable {
    public EventLabel[] items;

    public EventLabel[] getItems() {
        return items;
    }

    public void setItems(EventLabel[] items) {
        this.items = items;
    }
}
