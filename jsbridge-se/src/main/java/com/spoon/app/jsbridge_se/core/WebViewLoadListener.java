package com.spoon.app.jsbridge_se.core;

/**
 * author : zhangxin
 * date : 2020-04-02 23:00
 * description :
 */
public interface WebViewLoadListener {
    void onPageFinished(String url, boolean canGoBack,
                        boolean canGoForward);
}
