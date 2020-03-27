package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.app.Activity;
import android.content.Intent;

import com.n22.kdxf.speech.speech.IatDemo;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.Constants;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.HashMap;
import java.util.Map;

/**
 * author : zhangxin
 * date : 2020-03-27 13:15
 * description : 语音听写插件
 */
@BridgePlugin(name = "dictation")
public class DictationBridgeHandler extends BaseBridgeHandler {
    @Override
    public String[] authorization() {
        return new String[]{Permission.RECORD_AUDIO};
    }

    @Override
    public Boolean registerMaInterface() {
        return true;
    }

    @Override
    public void process(String data) {
        Intent intent = new Intent(getActivity(), IatDemo.class);
        getActivity().startActivityForResult(intent, Constants.OPEN_SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.OPEN_SPEECH_REQUEST_CODE) {
            if (null != data) {
                if (resultCode == Activity.RESULT_OK) {
                    Map<String, String> map = new HashMap<>();
                    map.put("reslut", data.getStringExtra("result"));
                    callBack.onCallBack(ResultUtil.success(map));
                }
            }
        }
    }
}
