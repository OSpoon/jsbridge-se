package com.spoon.app.jsbridge_n22.core.extension.bean;


import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-04-02 17:51
 * description :
 */
public class EventLabel extends Event implements Serializable {
    public String label;

    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}