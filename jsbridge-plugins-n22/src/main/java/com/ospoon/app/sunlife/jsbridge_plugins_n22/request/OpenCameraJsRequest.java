package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

/**
 * Created by gdk on 2020/03/27 14:49
 *
 * @author gdk
 * 打开相机的实体类
 */
public class OpenCameraJsRequest {
    private String openFlag;
    private int isCompress;

    public String getOpenFlag() {
        return openFlag == null ? "" : openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public int getIsCompress() {
        return isCompress;
    }

    public void setIsCompress(int isCompress) {
        this.isCompress = isCompress;
    }
}