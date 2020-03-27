package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.yanzhenjie.permission.runtime.Permission;

/**
 * 打开微信的桥接方法
 *
 * @author gdk
 */
@BridgePlugin(name = "openCamera")
public class OpenCameraBridgeHandler extends BaseBridgeHandler {
    private static final int REQUEST_CODE_OPEN_CAMERA = 100;
    /**
     * 权限数组,不申请权限设置为null
     *
     * @return
     */
    @Override
    public String[] authorization() {
        return new String[]{Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE};
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
