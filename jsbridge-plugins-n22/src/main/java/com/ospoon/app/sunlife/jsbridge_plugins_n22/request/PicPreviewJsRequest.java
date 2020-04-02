package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-04-02 10:31
 * description :
 */
public class PicPreviewJsRequest implements Serializable {

    private String[] images;

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
