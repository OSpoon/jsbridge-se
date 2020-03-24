package com.ospoon.app.sunlife.jsbridge_plugins_n22.emuns;

/**
 * author : zhangxin
 * date : 2020-03-24 15:36
 * description :
 */
public enum OpenBrowserEmun {

    SELF_WEB(1, "添加数据"),

    X5_WEB(2, "获取数据"),
    ;

    private Integer code;
    private String message;

    OpenBrowserEmun() {
    }

    OpenBrowserEmun(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
