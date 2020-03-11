package com.spoon.app.jsbridge_n22.core;

import android.content.Context;

public interface IWebView {

     void loadUrl(String url);

     void addJavascriptInterface(Object obj, String interfaceName);

     void evaluateJavascript(String var1, Object object);

     void callHandler(String handlerName, Object data, OnBridgeCallback responseCallback);

     Context getContext();

}
