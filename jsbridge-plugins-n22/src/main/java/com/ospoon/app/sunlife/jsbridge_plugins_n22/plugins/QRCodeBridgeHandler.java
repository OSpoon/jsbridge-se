package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.ospoon.app.sunlife.jsbridge_plugins_n22.response.QRCodeJSResponse;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import static android.app.Activity.RESULT_OK;

/**
 * author : zhangxin
 * date : 2020-03-19 15:16
 * description : 二维码识别插件
 */
@BridgePlugin(name = "scanQRCode")
public class QRCodeBridgeHandler extends BaseBridgeHandler {
    private static final int REQUEST_CODE_SCAN = 200;

    @Override
    public String[] authorization() {
        return new String[]{Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE};
    }

    @Override
    public Boolean registerMaInterface() {
        return true;
    }

    @Override
    public void process(String data) {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        getActivity().startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                QRCodeJSResponse qrCodeJSResponse = new QRCodeJSResponse();
                qrCodeJSResponse.setQrcode(content);
                callBack.onCallBack(ResultUtil.success(qrCodeJSResponse));
            }
        }
    }
}
