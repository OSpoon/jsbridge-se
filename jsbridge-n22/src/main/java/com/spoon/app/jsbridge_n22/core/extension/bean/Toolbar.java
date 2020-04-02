package com.spoon.app.jsbridge_n22.core.extension.bean;

import java.io.Serializable;

import static com.spoon.app.jsbridge_n22.core.extension.Constants.TOOLBAR_DEF_HEIGHT;

/**
 * author : zhangxin
 * date : 2020-04-02 17:53
 * description :
 */
public class Toolbar implements Serializable {
    public int height = TOOLBAR_DEF_HEIGHT;
    public String color;
    public String image;
    public String wwwImage;
    public double wwwImageDensity = 1;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWwwImage() {
        return wwwImage;
    }

    public void setWwwImage(String wwwImage) {
        this.wwwImage = wwwImage;
    }

    public double getWwwImageDensity() {
        return wwwImageDensity;
    }

    public void setWwwImageDensity(double wwwImageDensity) {
        this.wwwImageDensity = wwwImageDensity;
    }
}

