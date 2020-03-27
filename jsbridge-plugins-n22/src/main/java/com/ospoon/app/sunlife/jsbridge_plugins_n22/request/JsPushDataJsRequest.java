package com.ospoon.app.sunlife.jsbridge_plugins_n22.request;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Map;

/**
 * author : zhangxin
 * date : 2020-03-27 14:06
 * description :
 */
public class JsPushDataJsRequest implements Serializable {
    private String event;
    private Map<String, Object> data;

    public JsPushDataJsRequest() {
    }

    public JsPushDataJsRequest(String event, Map<String, Object> data) {
        this.event = event;
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
