package com.spoon.app.jsbridge_n22.demo;

import android.app.Application;

import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ClosePageBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.DeviceBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ToastBridgeHandler;
import com.spoon.app.jsbridge_n22.core.Bridge;

import java.util.Date;

public class AppContext extends Application {
    public final static String ROOT_URL = BuildConfig.ROOT_URL + "?time=" + new Date().getTime();

    @Override
    public void onCreate() {
        super.onCreate();
        Bridge.INSTANCE.registerHandler(ToastBridgeHandler.class,
                DeviceBridgeHandler.class,
                ClosePageBridgeHandler.class,
                OpenOtherBridgeHandler.class);
    }
}
