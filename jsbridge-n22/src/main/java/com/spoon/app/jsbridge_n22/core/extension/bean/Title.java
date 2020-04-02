package com.spoon.app.jsbridge_n22.core.extension.bean;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-04-02 17:53
 * description :
 */
public class Title implements Serializable {
    public String color;
    public String staticText;
    public boolean showPageTitle;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStaticText() {
        return staticText;
    }

    public void setStaticText(String staticText) {
        this.staticText = staticText;
    }

    public boolean isShowPageTitle() {
        return showPageTitle;
    }

    public void setShowPageTitle(boolean showPageTitle) {
        this.showPageTitle = showPageTitle;
    }
}
