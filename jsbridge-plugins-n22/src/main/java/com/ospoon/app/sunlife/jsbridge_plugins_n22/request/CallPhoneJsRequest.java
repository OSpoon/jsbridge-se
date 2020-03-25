package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

/**
 * Created by gdk on 2020/03/24 16:13
 *
 * @author gdk
 * description:打电话的Js调用实体类
 */
public class CallPhoneJsRequest {
    //电话号码
    private String phoneNumber;
    //分机号
    private String extensionNumber;

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getExtensionNumber() {
        return extensionNumber == null ? "" : extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }
}
