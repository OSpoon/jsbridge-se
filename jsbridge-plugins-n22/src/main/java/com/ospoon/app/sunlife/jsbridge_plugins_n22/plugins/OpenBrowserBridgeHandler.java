package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.core.security.httpcore.TextUtils;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.OpenBrowserJsRequest;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewActivity;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;

/**
 * author : zhangxin
 * date : 2020-03-24 15:35
 * description : 在js中打开新的webview插件
 */
@BridgePlugin(name = "openBrowser")
public class OpenBrowserBridgeHandler extends BaseBridgeHandler {
    @Override
    public String[] authorization() {
        return new String[0];
    }

    @Override
    public Boolean registerMaInterface() {
        return false;
    }

    @Override
    public void process(String data) {
        try {
            OpenBrowserJsRequest request = new Gson().fromJson(data, OpenBrowserJsRequest.class);
            if (!TextUtils.isEmpty(request.getUrl())) {
                BridgeWebViewActivity.start(getActivity(), request.getUrl());
            }
        } catch (Exception e) {
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
