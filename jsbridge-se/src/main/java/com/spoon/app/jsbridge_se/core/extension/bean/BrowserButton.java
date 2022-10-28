package com.spoon.app.jsbridge_se.core.extension.bean;

import java.io.Serializable;

import static com.spoon.app.jsbridge_se.core.extension.Constants.ALIGN_LEFT;

/**
 * author : zhangxin
 * date : 2020-04-02 17:52
 * description :
 */
public class BrowserButton extends Event implements Serializable {
    public String image;
    public String wwwImage;
    public String imagePressed;
    public String wwwImagePressed;
    public double wwwImageDensity = 1;
    public String align = ALIGN_LEFT;

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

    public String getImagePressed() {
        return imagePressed;
    }

    public void setImagePressed(String imagePressed) {
        this.imagePressed = imagePressed;
    }

    public String getWwwImagePressed() {
        return wwwImagePressed;
    }

    public void setWwwImagePressed(String wwwImagePressed) {
        this.wwwImagePressed = wwwImagePressed;
    }

    public double getWwwImageDensity() {
        return wwwImageDensity;
    }

    public void setWwwImageDensity(double wwwImageDensity) {
        this.wwwImageDensity = wwwImageDensity;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }
}