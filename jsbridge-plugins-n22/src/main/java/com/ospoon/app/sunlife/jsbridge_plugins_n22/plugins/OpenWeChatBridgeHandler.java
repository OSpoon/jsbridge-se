package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;

import com.blankj.utilcode.util.ToastUtils;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;

/**
 * 打开微信的桥接方法
 *
 * @author gdk
 */
@BridgePlugin(name = "openWeChat")
public class OpenWeChatBridgeHandler extends BaseBridgeHandler {

    /**
     * 权限数组,不申请权限设置为null
     *
     * @return
     */
    @Override
    public String[] authorization() {
        return null;
    }

    /**
     * 是否支持通过onActivityResult回调数据
     *
     * @return
     */
    @Override
    public Boolean registerMaInterface() {
        return false;
    }

    /**
     * 业务流程
     *
     * @param data
     */
    @Override
    public void process(String data) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            getActivity().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtils.showShort("检查到您手机未安装微信，请安装后使用该功能");
        }
    }

    /**
     * 接收回调数据
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
