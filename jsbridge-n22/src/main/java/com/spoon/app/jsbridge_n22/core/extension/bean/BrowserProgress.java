package com.spoon.app.jsbridge_n22.core.extension.bean;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-04-02 17:52
 * description :
 */
public class BrowserProgress implements Serializable {
    public boolean showProgress;
    public String progressBgColor;
    public String progressColor;

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public String getProgressBgColor() {
        return progressBgColor;
    }

    public void setProgressBgColor(String progressBgColor) {
        this.progressBgColor = progressBgColor;
    }

    public String getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(String progressColor) {
        this.progressColor = progressColor;
    }
}
