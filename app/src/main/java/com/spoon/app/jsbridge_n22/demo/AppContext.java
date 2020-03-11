package com.spoon.app.jsbridge_n22.demo;

import android.app.Application;

import com.spoon.app.jsbridge_n22.core.Bridge;
import com.spoon.app.jsbridge_n22.core.BridgeHandler;
import com.spoon.app.jsbridge_n22.demo.plugins.ToastBridgeHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppContext extends Application {
    public final static String ROOT_URL = "http://192.168.199.163:9999?time=" + new Date().getTime();
    public final static String HANDLER_NAME_TOAST = "toast";

    @Override
    public void onCreate() {
        super.onCreate();
        Map<String, BridgeHandler> handlerMap = new HashMap<>();
        handlerMap.put(HANDLER_NAME_TOAST, new ToastBridgeHandler());
        Bridge.INSTANCE.registerHandler(handlerMap);
    }
}
