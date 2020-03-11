package com.spoon.app.jsbridge_n22.bean;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private T content;
    private String error;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
