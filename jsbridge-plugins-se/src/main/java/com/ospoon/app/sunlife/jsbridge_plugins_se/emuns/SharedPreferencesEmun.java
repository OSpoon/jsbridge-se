package com.ospoon.app.sunlife.jsbridge_plugins_se.emuns;

/**
 * author : zhangxin
 * date : 2020-03-24 14:41
 * description :
 */
public enum SharedPreferencesEmun {
    PUT(1, "添加数据"),

    GET(2, "获取数据"),

    CONTAINS(3, "是否存在该 key"),

    REMOVE(4, "移除该 key"),

    CLEAR(5, "清除所有数据"),
    ;

    private Integer code;
    private String message;

    SharedPreferencesEmun() {
    }

    SharedPreferencesEmun(int code, String message) {
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
