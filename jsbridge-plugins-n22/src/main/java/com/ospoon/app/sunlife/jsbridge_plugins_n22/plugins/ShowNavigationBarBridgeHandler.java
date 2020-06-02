package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.google.gson.Gson;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.bean.NavigationBarDataBean;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;

import java.io.Serializable;

/**
 * Created by gdk on 2020/6/2 13:56
 * @author 郭登科
 * 显示原生导航栏的插件
 */
@BridgePlugin(name = "showNavigationBar")
public class ShowNavigationBarBridgeHandler extends BaseBridgeHandler {
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
        NavigationBarDataBean navigationBarDataBean = new Gson().fromJson(data, NavigationBarDataBean.class);
        Intent intent = new Intent();
        intent.setAction("com.n22.jsbridge.JS_TITLE_BAR_DATA");
        intent.putExtra("navigationBarData", (Serializable) navigationBarDataBean);
        getActivity().sendBroadcast(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}