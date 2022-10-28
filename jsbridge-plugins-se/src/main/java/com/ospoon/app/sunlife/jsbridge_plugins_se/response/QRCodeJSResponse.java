package com.ospoon.app.sunlife.jsbridge_plugins_se.response;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-03-19 15:19
 * description :
 */
public class QRCodeJSResponse implements Serializable {
    private String qrcode;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
