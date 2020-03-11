package com.spoon.app.jsbridge_n22.demo.plugins;

import android.content.Intent;
import android.widget.Toast;

import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;

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
        Toast.makeText(mContext,"js data:" + data, Toast.LENGTH_SHORT).show();
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
