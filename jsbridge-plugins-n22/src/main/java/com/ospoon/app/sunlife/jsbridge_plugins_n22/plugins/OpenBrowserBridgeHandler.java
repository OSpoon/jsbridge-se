package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.emuns.OpenBrowserEmun;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.OpenBrowserJsRequest;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewActivity;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewCustomActivity;
import com.spoon.app.jsbridge_n22.activity.X5WebViewActivity;
import com.spoon.app.jsbridge_n22.activity.X5WebViewCustomActivity;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.core.extension.bean.Options;
import com.spoon.app.jsbridge_n22.core.extension.bean.Title;
import com.spoon.app.jsbridge_n22.core.extension.bean.Toolbar;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;

/**
 * author : zhangxin
 * date : 2020-03-24 15:35
 * description : 在js中打开新的webview插件
 */
@BridgePlugin(name = "openBrowser")
public class OpenBrowserBridgeHandler extends BaseBridgeHandler {
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
            OpenBrowserJsRequest request = new Gson().fromJson(data, OpenBrowserJsRequest.class);
            int mode = request.getMode();
            String url = request.getUrl();
            Options options = null;
            if (request.isCustom()) {
                options = new Options();

                Toolbar toolbar = new Toolbar();
                toolbar.height = request.getToolbar().getHeight();
                toolbar.color = request.getToolbar().getColor();
                options.toolbar = toolbar;

                Title title = new Title();
                title.color = request.getTitle().getColor();
                title.staticText = request.getTitle().getStaticText();
                options.title = title;

                options.isShowClose = request.isShowClose();
                options.isShowBack = request.isShowBack();
                options.isShowShare = request.isShowShare();
            }
            if (mode == OpenBrowserEmun.SELF_WEB.getCode()) {
                if (options != null) {
                    BridgeWebViewCustomActivity.start(getActivity(), url, options);
                } else {
                    BridgeWebViewActivity.start(getActivity(), url);
                }
            } else if (mode == OpenBrowserEmun.X5_WEB.getCode()) {
                if (options != null) {
                    X5WebViewCustomActivity.start(getActivity(), url, options);
                } else {
                    X5WebViewActivity.start(getActivity(), url);
                }
            }
        } catch (Exception e) {
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
