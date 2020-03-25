package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

/**
 * Created by gdk on 2020/03/24 16:13
 *
 * @author gdk
 * description:打电话的Js调用实体类
 */
public class OpenWechatJsRequest {
    //电话号码
    private String mode;
    //分机号
    private String WeChat;

    public String getMode() {
        return mode == null ? "" : mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getWeChat() {
        return WeChat == null ? "" : WeChat;
    }

    public void setWeChat(String weChat) {
        WeChat = weChat;
    }
}
