package com.spoon.app.jsbridge_n22.bean;

import java.io.Serializable;

public class MessageEvent implements Serializable {
    private String parentActivityId;
    private String handlerName;
    private Object data;

    public MessageEvent(String parentActivityId, String handlerName, Object data) {
        this.parentActivityId = parentActivityId;
        this.handlerName = handlerName;
        this.data = data;
    }

    public MessageEvent(Object data) {
        this.data = data;
    }

    public String getParentActivityId() {
        return parentActivityId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
