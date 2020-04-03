package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

/**
 * Created by gdk on 2020/03/25 16:37
 *
 * @author gdk
 */
public class ShareWeChatJsRequest {

    public String platform;    // 分享平台
    public String title;       // 分享标题
    public String desc;        // 分享内容
    public String iconUrl;   // 分享的图片url，可以使本地图片路径，也可以是网络图片路径
    public String webPageUrl;   // 分享要跳转的网页

    public String getPlatform() {
        return platform == null ? "" : platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWebPageUrl() {
        return webPageUrl == null ? "" : webPageUrl;
    }

    public void setWebPageUrl(String webPageUrl) {
        this.webPageUrl = webPageUrl;
    }
}
