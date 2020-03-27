package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;

import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.Constants;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.JsPushDataJsRequest;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;

import java.util.Map;

/**
 * author : zhangxin
 * date : 2020-03-27 14:04
 * description : 用于Js推送数据后注册响应广播的用户接收
 */
@BridgePlugin(name = "pushData")
public class JsPushDataBridgeHandler extends BaseBridgeHandler {
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
        JsPushDataJsRequest request = new Gson().fromJson(data, JsPushDataJsRequest.class);
        Intent intent = new Intent();
        intent.setAction(Constants.JSBRIDGEN22_JS_PUSH_DATA_ACTION);
        intent.putExtra("event", request.getEvent());
        Map<String, Object> datas = request.getData();
        for (Map.Entry<String, Object> entry : datas.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                intent.putExtra(key, String.valueOf(value));
            } else if (value instanceof Integer) {
                intent.putExtra(key, (Integer) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(key, (Boolean) value);
            }
        }
        getActivity().sendBroadcast(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
