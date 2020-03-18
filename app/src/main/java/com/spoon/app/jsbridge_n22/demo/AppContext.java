package com.spoon.app.jsbridge_n22.demo;

import android.app.Application;

import com.spoon.app.jsbridge_n22.core.Bridge;
import com.spoon.app.jsbridge_n22.demo.plugins.OpenOtherBridgeHandler;
import com.spoon.app.jsbridge_n22.demo.plugins.ToastBridgeHandler;
import com.spoon.app.jsbridge_n22.demo.plugins.DeviceBridgeHandler;

import java.util.Date;

public class AppContext extends Application {
    public final static String ROOT_URL = "http://192.168.199.163:9999?time=" + new Date().getTime();

    @Override
    public void onCreate() {
        super.onCreate();
        Bridge.INSTANCE.registerHandler(ToastBridgeHandler.class,
                DeviceBridgeHandler.class,
                OpenOtherBridgeHandler.class);
    }
}
