package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-03-20 15:03
 * description :
 */
public class LocationJsRequest implements Serializable {
    //设置是否返回地址信息
    private boolean needAddress;
    //设置是否允许模拟位置
    private boolean mockEnable;
    //超时时间
    private String httpTimeOut;

    public boolean isNeedAddress() {
        return needAddress;
    }

    public void setNeedAddress(boolean needAddress) {
        this.needAddress = needAddress;
    }

    public boolean isMockEnable() {
        return mockEnable;
    }

    public void setMockEnable(boolean mockEnable) {
        this.mockEnable = mockEnable;
    }

    public String getHttpTimeOut() {
        return httpTimeOut;
    }

    public void setHttpTimeOut(String httpTimeOut) {
        this.httpTimeOut = httpTimeOut;
    }
}
