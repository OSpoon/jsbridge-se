package com.ospoon.app.sunlife.jsbridge_plugins_se.plugins;

import android.content.Intent;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_se.emuns.SharedPreferencesEmun;
import com.ospoon.app.sunlife.jsbridge_plugins_se.request.SharedPreferencesJsRequest;
import com.spoon.app.jsbridge_se.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_se.core.BridgePlugin;
import com.spoon.app.jsbridge_se.utils.ResultUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * author : zhangxin
 * date : 2020-03-24 14:36
 * description : 原生SharedPreferences存储
 */
@BridgePlugin(name = "storage")
public class SharedPreferencesBridgeHandler extends BaseBridgeHandler {
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
            SharedPreferencesJsRequest request = new Gson().fromJson(data, SharedPreferencesJsRequest.class);
            SPUtils instance = SPUtils.getInstance();
            //添加数据
            if (request.getMode() == SharedPreferencesEmun.PUT.getCode()) {
                instance.put(request.getKey(), request.getValue());
            }
            //获取数据
            else if (request.getMode() == SharedPreferencesEmun.GET.getCode()) {
                String string = instance.getString(request.getKey());
                Map<String, String> map = new HashMap<>();
                map.put("value", string);
                callBack.onCallBack(ResultUtil.success(map));
            }
            //查看key是否存在
            else if (request.getMode() == SharedPreferencesEmun.CONTAINS.getCode()) {
                boolean contains = instance.contains(request.getKey());
                Map<String, Boolean> map = new HashMap<>();
                map.put("contains", contains);
                callBack.onCallBack(ResultUtil.success(map));
            }
            //删除指定key的数据
            else if (request.getMode() == SharedPreferencesEmun.REMOVE.getCode()) {
                instance.remove(request.getKey());
            }
            //清空所有数据
            else if (request.getMode() == SharedPreferencesEmun.CLEAR.getCode()) {
                instance.clear(true);
            }
        } catch (Exception e) {
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
