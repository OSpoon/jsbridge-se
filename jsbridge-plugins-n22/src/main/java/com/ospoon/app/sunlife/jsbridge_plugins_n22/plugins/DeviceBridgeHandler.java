package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.blankj.utilcode.util.DeviceUtils;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.response.DeviceJsResponse;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;

/**
 * author : zhangxin
 * date : 2020-03-18 23:34
 * description :
 */
@BridgePlugin(name = "device")
public class DeviceBridgeHandler extends BaseBridgeHandler {
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
        DeviceJsResponse response = new DeviceJsResponse();
        response.setDeviceRooted(DeviceUtils.isDeviceRooted());
        response.setAdbEnabled(DeviceUtils.isAdbEnabled());
        response.setsDKVersionName(DeviceUtils.getSDKVersionName());
        response.setsDKVersionCode(DeviceUtils.getSDKVersionCode());
        response.setAndroidID(DeviceUtils.getAndroidID());
        response.setMacAddress(DeviceUtils.getMacAddress());
        response.setManufacturer(DeviceUtils.getManufacturer());
        response.setModel(DeviceUtils.getModel());
        response.setaBIs(DeviceUtils.getABIs());
        response.setTablet(DeviceUtils.isTablet());
        response.setUniqueDeviceId(DeviceUtils.getUniqueDeviceId());
        callBack.onCallBack(ResultUtil.success(response));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
