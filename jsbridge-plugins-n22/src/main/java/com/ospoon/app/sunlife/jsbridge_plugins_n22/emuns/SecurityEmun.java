package com.ospoon.app.sunlife.jsbridge_plugins_n22.emuns;

/**
 * author : zhangxin
 * date : 2020-03-24 15:36
 * description :
 */
public enum SecurityEmun {

    SIGN(1, "获取MD5T验签"),

    ENCODE(2, "3des加密"),

    DECODE(3,"3des解密"),
    ;

    private Integer code;
    private String message;

    SecurityEmun() {
    }

    SecurityEmun(int code, String message) {
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
