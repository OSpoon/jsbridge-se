package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.ospoon.app.sunlife.jsbridge_plugins_n22.core.security.SecurityUtil;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.core.security.httpcore.TextUtils;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.emuns.SecurityEmun;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * author : zhangxin
 * date : 2020-03-24 16:14
 * description : 调用原3des和验签生成插件
 */
@BridgePlugin(name = "security")
public class SecurityBridgeHandler extends BaseBridgeHandler {

    private final static String DEFAULT_KEY = "MOAPPINTERFACE2017#@!%88";

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
        try {
            JSONObject jsonObject = new JSONObject(data);
            int mode = (Integer) jsonObject.get("mode");
            String key = (String) jsonObject.get("key");
            String content = (String) jsonObject.get("content");
            security(mode, key, content);
        } catch (JSONException e) {
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }

    public void security(int mode, String key, String json) {
        if("default".equalsIgnoreCase(key)){
            key = DEFAULT_KEY;
        }
        if (mode == SecurityEmun.ENCODE.getCode()) {
            //报文加密
            try {
                String encoder = SecurityUtil.encoder(key, json);
                Map<String, String> map = new HashMap<>();
                map.put("encoder", encoder);
                callBack.onCallBack(ResultUtil.success(map));
            } catch (Exception e) {
                callBack.onCallBack(ResultUtil.error("1", e.getMessage()));
            }
        } else if (mode == SecurityEmun.DECODE.getCode()) {
            //报文解密
            try {
                String decoder = SecurityUtil.decoder(key, json);
                Map<String, String> map = new HashMap<>();
                map.put("decoder", decoder);
                callBack.onCallBack(ResultUtil.success(map));
            } catch (Exception e) {
                callBack.onCallBack(ResultUtil.error("1", e.getMessage()));
            }
        } else if (mode == SecurityEmun.SIGN.getCode()) {
            //获取验签
            try {
                String sign = SecurityUtil.getSign(key, json);
                Map<String, String> map = new HashMap<>();
                map.put("sign", sign);
                callBack.onCallBack(ResultUtil.success(map));
            } catch (Exception e) {
                callBack.onCallBack(ResultUtil.error("1", e.getMessage()));
            }
        }
    }
}
