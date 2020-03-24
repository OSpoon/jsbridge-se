package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.ospoon.app.sunlife.jsbridge_plugins_n22.emuns.OpenBrowserEmun;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewActivity;
import com.spoon.app.jsbridge_n22.activity.X5WebViewActivity;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
            JSONObject jsonObject = new JSONObject(data);
            int mode = (Integer) jsonObject.get("mode");
            String url = (String) jsonObject.get("url");
            if (mode == OpenBrowserEmun.SELF_WEB.getCode()) {
                BridgeWebViewActivity.start(getActivity(), url);
            } else if (mode == OpenBrowserEmun.X5_WEB.getCode()) {
                X5WebViewActivity.start(getActivity(), url);
            }
        } catch (JSONException e) {
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
