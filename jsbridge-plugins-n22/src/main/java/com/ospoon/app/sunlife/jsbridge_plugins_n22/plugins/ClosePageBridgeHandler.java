package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.Utils;

/**
 * author : zhangxin
 * date : 2020-03-19 14:55
 * description :
 */
@BridgePlugin(name = "close")
public class ClosePageBridgeHandler extends BaseBridgeHandler {
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
            Utils.postParentWebViewMessage(getActivity().getParentActivityId(), "closeCallBack", "200");
            getActivity().finish();
        } catch (Exception e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
