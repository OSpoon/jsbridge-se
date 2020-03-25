package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

/**
 * Created by gdk on 2020/03/24 16:13
 *
 * @author gdk
 * description:发短信的Js调用实体类
 */
public class SendMsgJsRequest {
    //电话号码
    private String phoneNumber;
    //发送的信息
    private String msgInfo;

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMsgInfo() {
        return msgInfo == null ? "" : msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
}
