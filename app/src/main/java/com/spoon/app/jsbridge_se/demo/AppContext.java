package com.spoon.app.jsbridge_se.demo;

import android.support.multidex.MultiDexApplication;

import com.ospoon.app.sunlife.jsbridge_plugins_se.plugins.ClosePageBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_se.plugins.DeviceBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_se.plugins.JsPushDataBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_se.plugins.QRCodeBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_se.plugins.ReloadWebViewBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_se.plugins.SharedPreferencesBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_se.plugins.ToastBridgeHandler;
import com.spoon.app.jsbridge_se.core.Bridge;
import com.tencent.bugly.Bugly;

import java.util.Date;

public class AppContext extends MultiDexApplication {
    public final static String ROOT_URL = BuildConfig.ROOT_URL + "?time=" + new Date().getTime();

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bugly
        Bugly.init(getApplicationContext(), "b05b3b76f4", true);
        //注册JsBridge-se插件
        Bridge.INSTANCE.registerHandler(ToastBridgeHandler.class,
                DeviceBridgeHandler.class,
                ClosePageBridgeHandler.class,
                QRCodeBridgeHandler.class,
                SharedPreferencesBridgeHandler.class,
                OpenOtherBridgeHandler.class,
                JsPushDataBridgeHandler.class,
                ReloadWebViewBridgeHandler.class);
    }
}
