package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.megvii.licensemanager.Manager;
import com.megvii.livenessdetection.LivenessLicenseManager;
import com.megvii.livenesslib.LivenessActivity;
import com.megvii.livenesslib.util.ConUtil;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.Constants;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.HashMap;
import java.util.Map;

/**
 * author : zhangxin
 * date : 2020-03-27 10:58
 * description : 人脸识别插件
 */
@BridgePlugin(name = "faceScan")
public class FaceScanBridgerHandler extends BaseBridgeHandler {
    @Override
    public String[] authorization() {
        return Permission.Group.CAMERA;
    }

    @Override
    public Boolean registerMaInterface() {
        return true;
    }

    @Override
    public void process(String data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Manager manager = new Manager(getActivity());
                LivenessLicenseManager licenseManager = new LivenessLicenseManager(getActivity());
                manager.registerLicenseManager(licenseManager);
                String uuid = ConUtil.getUUIDString(getActivity());
                manager.takeLicenseFromNetwork(uuid);
                if (licenseManager.checkCachedLicense() > 0) {
                    getActivity().startActivityForResult(new Intent(getActivity(), LivenessActivity.class), Constants.OPEN_FACE_SCAN_REQUEST_CODE);
                } else {
                    callBack.onCallBack(ResultUtil.error("1", "联网授权失败！请检查网络或找服务商"));
                }
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.OPEN_FACE_SCAN_REQUEST_CODE) {
            if (null != data) {
                String result = data.getStringExtra("result");
                if (resultCode == Activity.RESULT_OK) {
                    if (!TextUtils.isEmpty(result)) {
                        Map<String, String> map = new HashMap<>();
                        map.put("faceimg_base64", result);
                        callBack.onCallBack(ResultUtil.success(map));
                    }
                } else if (resultCode == LivenessActivity.RESULT_ERROR) {
                    callBack.onCallBack(ResultUtil.error("1", result));
                }
            }
        }
    }
}
