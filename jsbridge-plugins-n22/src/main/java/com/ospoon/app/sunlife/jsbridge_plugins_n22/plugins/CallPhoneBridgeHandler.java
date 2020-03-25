package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;

import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.CallPhoneJsRequest;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.yanzhenjie.permission.runtime.Permission;

/**
 * 打电话的桥接回调方法
 *
 * @author gdk
 */
@BridgePlugin(name = "callPhone")
public class CallPhoneBridgeHandler extends BaseBridgeHandler {

    /**
     * 权限数组,不申请权限设置为null
     *
     * @return
     */
    @Override
    public String[] authorization() {
        return new String[]{Permission.CALL_PHONE};
    }

    /**
     * 是否支持通过onActivityResult回调数据
     *
     * @return
     */
    @Override
    public Boolean registerMaInterface() {
        return false;
    }

    /**
     * 业务流程
     *
     * @param data
     */
    @SuppressLint("MissingPermission")
    @Override
    public void process(String data) {
        CallPhoneJsRequest request = new Gson().fromJson(data, CallPhoneJsRequest.class);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + request.getPhoneNumber()));
        getActivity().startActivity(intent);
    }

    /**
     * 接收回调数据
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
