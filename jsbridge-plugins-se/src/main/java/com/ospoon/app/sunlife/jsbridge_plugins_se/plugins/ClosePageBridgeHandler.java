package com.ospoon.app.sunlife.jsbridge_plugins_se.plugins;

import android.content.Intent;

import com.spoon.app.jsbridge_se.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_se.core.BridgePlugin;
import com.spoon.app.jsbridge_se.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-03-19 14:55
 * description :
 */
@BridgePlugin(name = "close")
public class ClosePageBridgeHandler extends BaseBridgeHandler {
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
        JSONObject json = null;
        try {
            json = new JSONObject(data);
        } catch (JSONException e) {
        }
        try {
            if (json != null) {
                if ("reloadPage".equals(json.get("data"))) {
                    Intent intent = new Intent();
                    intent.setAction("com.se.jsbridge.JS_RELOAD_PAGE_DATA");
                    intent.putExtra("reloadPage", "reloadPage");
                    getActivity().sendBroadcast(intent);
                } else {
                    Utils.postParentWebViewMessage(getActivity().getParentActivityId(), "GDINativePushData", (String) json.get("data"));
                }
            } else {
                Utils.postParentWebViewMessage(getActivity().getParentActivityId(), "200");
            }
            getActivity().finish();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            getActivity().finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
