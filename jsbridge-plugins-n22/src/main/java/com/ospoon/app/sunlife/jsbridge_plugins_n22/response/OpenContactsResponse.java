package com.ospoon.app.sunlife.jsbridge_plugins_n22.response;

/**
 * Created by gdk on 2020/03/25 15:02
 *
 * @author gdk
 * 联系人返回数据
 */
public class OpenContactsResponse {

    private String name;
    private String mobilePhone;
    private String id;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone == null ? "" : mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
