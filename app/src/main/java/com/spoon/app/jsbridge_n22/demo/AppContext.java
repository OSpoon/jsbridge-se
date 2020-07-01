package com.spoon.app.jsbridge_n22.demo;

import android.support.multidex.MultiDexApplication;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.CallPhoneBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ClosePageBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.DeviceBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.DictationBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.FaceScanBridgerHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.IDCardScanBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.JsPushDataBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.LocationBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.OpenBrowserBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.OpenCameraBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.OpenContactBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.OpenContactsBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.OpenWeChatBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.PicPreviewBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.QRCodeBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ReloadWebViewBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.SecurityBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.SendMsgBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ShareWechatBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.SharedPreferencesBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ShowLoadingBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ShowNavigationBarBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.SwitchScreenBridgeHandler;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins.ToastBridgeHandler;
import com.spoon.app.jsbridge_n22.core.Bridge;
import com.tencent.bugly.Bugly;

import java.util.Date;

public class AppContext extends MultiDexApplication {
    public final static String ROOT_URL = BuildConfig.ROOT_URL + "?time=" + new Date().getTime();

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bugly
        Bugly.init(getApplicationContext(), "b05b3b76f4", true);
        //注册科大讯飞语音听写
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5aa5fa15");
        //注册JsBridge-n22插件
        Bridge.INSTANCE.registerHandler(ToastBridgeHandler.class,
                DeviceBridgeHandler.class,
                ClosePageBridgeHandler.class,
                QRCodeBridgeHandler.class,
                LocationBridgeHandler.class,
                SharedPreferencesBridgeHandler.class,
                OpenBrowserBridgeHandler.class,
                SecurityBridgeHandler.class,
                OpenOtherBridgeHandler.class,
                CallPhoneBridgeHandler.class,
                SendMsgBridgeHandler.class,
                OpenContactsBridgeHandler.class,
                OpenWeChatBridgeHandler.class,
                SwitchScreenBridgeHandler.class,
                IDCardScanBridgeHandler.class,
                ShareWechatBridgeHandler.class,
                IDCardScanBridgeHandler.class,
                FaceScanBridgerHandler.class,
                OpenCameraBridgeHandler.class,
                DictationBridgeHandler.class,
                JsPushDataBridgeHandler.class,
                PicPreviewBridgeHandler.class,
                ShowNavigationBarBridgeHandler.class,
                ShowLoadingBridgeHandler.class,
                OpenContactBridgeHandler.class,
                ReloadWebViewBridgeHandler.class);
    }
}
