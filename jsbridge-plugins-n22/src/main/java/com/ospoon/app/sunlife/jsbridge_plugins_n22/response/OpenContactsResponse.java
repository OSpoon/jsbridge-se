package com.ospoon.app.sunlife.jsbridge_plugins_n22.response;

/**
 * Created by gdk on 2020/03/25 15:02
 * @author gdk
 * 联系人返回数据
 */
public class OpenContactsResponse {

    private String displayName;
    private String number;

    public String getDisplayName() {
        return displayName == null ? "" : displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNumber() {
        return number == null ? "" : number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
