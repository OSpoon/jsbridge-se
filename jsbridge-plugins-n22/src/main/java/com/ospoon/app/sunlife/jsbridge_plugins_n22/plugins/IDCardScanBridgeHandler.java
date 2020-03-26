package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.app.Activity;
import android.content.Intent;

import com.megvii.demo.activity.IDCardDetectActivity;
import com.megvii.demo.utils.Configuration;
import com.megvii.idcardquality.IDCardQualityLicenseManager;
import com.megvii.licensemanager.Manager;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.Constants;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * author : zhangxin
 * date : 2020-03-26 15:28
 * description : 身份证扫描
 */
@BridgePlugin(name = "IDCardScan")
public class IDCardScanBridgeHandler extends BaseBridgeHandler {

    private IDCardQualityLicenseManager mIdCardLicenseManager;

    @Override
    public String[] authorization() {
        return Permission.Group.CAMERA;
    }

    @Override
    public Boolean registerMaInterface() {
        return true;
    }

    @Override
    public void process(final String data) {
        try {
            JSONObject json = new JSONObject(data);
            Boolean isVertical = json.getBoolean("isVertical");
            int cardType = json.getInt("cardType");
            //1、初始化配置
            initConfig(isVertical, cardType);
            //2、请求授权信息
            startGetLicense();
        } catch (JSONException e) {
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }
    }

    private void initConfig(Boolean isVertical, int cardType) {
        Configuration.setIsVertical(getActivity(), isVertical);
        Configuration.setCardType(getActivity(), cardType);
    }

    private void startGetLicense() {
        mIdCardLicenseManager = new IDCardQualityLicenseManager(
                getActivity());
        long status = 0;
        try {
            status = mIdCardLicenseManager.checkCachedLicense();
        } catch (Throwable e) {
            callBack.onCallBack(ResultUtil.error("1", e.getMessage()));
        }
        if (status > 0) {//大于0，已授权或者授权未过期
            Intent intent = new Intent(getActivity(), IDCardDetectActivity.class);
            getActivity().startActivityForResult(intent, Constants.OPEN_IDCARD_SCAN_REQUEST_CODE);
        } else { //需要重新授权
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        getLicense();
                    } catch (Throwable e) {
                        callBack.onCallBack(ResultUtil.error("1", e.getMessage()));
                    }
                }
            }).start();
        }
    }

    private void getLicense() {
        Manager manager = new Manager(getActivity());
        manager.registerLicenseManager(mIdCardLicenseManager);
        String uuid = Configuration.getUUID(getActivity());
        String authMsg = mIdCardLicenseManager.getContext(uuid);
        manager.takeLicenseFromNetwork(authMsg);
        if (mIdCardLicenseManager.checkCachedLicense() > 0) {//大于0，已授权或者授权未过期
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getActivity(), IDCardDetectActivity.class);
                    getActivity().startActivityForResult(intent, Constants.OPEN_IDCARD_SCAN_REQUEST_CODE);
                }
            });
        } else {
            callBack.onCallBack(ResultUtil.error("400", "授权失败,请联系服务商"));
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.OPEN_IDCARD_SCAN_REQUEST_CODE) {
            if (null != data) {
                if (resultCode == Activity.RESULT_OK) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("portraitimg_base64",data.getStringExtra("portraitimg_bitmap"));
                    map.put("idcardimg_base64",data.getStringExtra("idcardimg_bitmap"));
                    callBack.onCallBack(ResultUtil.success(map));
                }
            }
        }
    }
}
