package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.ToastJsRequest;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;

@BridgePlugin(name = "toast")
public class ToastBridgeHandler extends BaseBridgeHandler {

    /**
     * 权限数组,不申请权限设置为null
     * @return
     */
    @Override
    public String[] authorization() {
        return null;
    }

    /**
     * 是否支持通过onActivityResult回调数据
     * @return
     */
    @Override
    public Boolean registerMaInterface() {
        return false;
    }

    /**
     * 业务流程
     * @param data
     */
    @Override
    public void process(String data) {
        ToastJsRequest request = new Gson().fromJson(data, ToastJsRequest.class);
        Toast.makeText(getActivity(),request.getText(), request.getDuration()).show();
    }

    /**
     * 接收回调数据
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
