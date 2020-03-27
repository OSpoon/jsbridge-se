package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;
import android.net.Uri;

import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.SendMsgJsRequest;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;

/**
 * 发送信息的方法
 * @author gdk
 */
@BridgePlugin(name = "sendMsg")
public class SendMsgBridgeHandler extends BaseBridgeHandler {
    /**
     * 权限数组,不申请权限设置为null
     *
     * @return
     */
    @Override
    public String[] authorization() {
        return null;
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
    @Override
    public void process(String data) {
        SendMsgJsRequest request = new Gson().fromJson(data, SendMsgJsRequest.class);
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + request.getPhoneNumber()));
        intent.putExtra("sms_body", request.getMsgInfo());
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
