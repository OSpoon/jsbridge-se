package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-04-03 14:21
 * description :
 */
public class OpenBrowserJsRequest implements Serializable {
    private int mode;
    private String url;
    private boolean isCustom = false;
    private boolean isShowClose = false;
    private boolean isShowBack = true;
    private boolean isShowShare = true;
    private Toolbar toolbar;
    private Title title;

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public boolean isShowClose() {
        return isShowClose;
    }

    public void setShowClose(boolean showClose) {
        isShowClose = showClose;
    }

    public boolean isShowBack() {
        return isShowBack;
    }

    public void setShowBack(boolean showBack) {
        isShowBack = showBack;
    }

    public boolean isShowShare() {
        return isShowShare;
    }

    public void setShowShare(boolean showShare) {
        isShowShare = showShare;
    }

    public class Toolbar implements Serializable {
        private int height = 44;
        private String color = "#f0f0f0ff";

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public class Title implements Serializable {
        private String color = "#003264ff";
        private String staticText = "";

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
    }

}
