package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.google.gson.Gson;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.bean.NavigationBarDataBean;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.LoadingDialog;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by gdk on 2020/6/2 13:56
 *
 * @author 郭登科
 * 显示原生loading的插件
 */
@BridgePlugin(name = "showLoading")
public class ShowLoadingBridgeHandler extends BaseBridgeHandler {
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
        LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        try {
            JSONObject jsonObject = new JSONObject(data);
            String isShow = (String) jsonObject.get("isShow");
            if (loadingDialog != null && !getActivity().isFinishing()) {
                if ("1".equals(isShow)) {
                    loadingDialog.show();
                } else {
                    loadingDialog.dismiss();
                }
            }
        } catch (JSONException e) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}