package com.spoon.app.jsbridge_n22.demo;

import android.app.Application;

import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ClosePageBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.DeviceBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.LocationBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.QRCodeBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.SharedPreferencesBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ToastBridgeHandler;
import com.spoon.app.jsbridge_n22.core.Bridge;
import com.tencent.bugly.Bugly;

import java.util.Date;

public class AppContext extends Application {
    public final static String ROOT_URL = BuildConfig.ROOT_URL + "?time=" + new Date().getTime();

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bugly
        Bugly.init(getApplicationContext(), "b05b3b76f4", true);
        //注册JsBridge-n22插件
        Bridge.INSTANCE.registerHandler(ToastBridgeHandler.class,
                DeviceBridgeHandler.class,
                ClosePageBridgeHandler.class,
                QRCodeBridgeHandler.class,
                LocationBridgeHandler.class,
                SharedPreferencesBridgeHandler.class,
                OpenOtherBridgeHandler.class);
    }
}
