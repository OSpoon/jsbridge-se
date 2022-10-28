package com.ospoon.app.sunlife.jsbridge_plugins_se.plugins;

import android.content.Intent;

import com.spoon.app.jsbridge_se.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_se.core.BridgePlugin;
import com.spoon.app.jsbridge_se.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author : zhangxin
 * date : 2020-03-19 14:55
 * description :
 */
@BridgePlugin(name = "reload")
public class ReloadWebViewBridgeHandler extends BaseBridgeHandler {
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
        getActivity().reload();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
