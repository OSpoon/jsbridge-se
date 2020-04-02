package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.PicPreviewJsRequest;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;

import java.util.Arrays;

import cc.shinichi.library.ImagePreview;

/**
 * author : zhangxin
 * date : 2020-04-02 10:12
 * description : 图片预览插件
 */
@BridgePlugin(name="picPreview")
public class PicPreviewBridgeHandler extends BaseBridgeHandler {
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
        // 仅需一行代码,默认配置为：
        //      显示顶部进度指示器、
        //      显示右侧下载按钮、
        //      隐藏左侧关闭按钮、
        //      开启点击图片关闭、
        //      关闭下拉图片关闭、
        //      加载方式为手动模式
        //      加载原图的百分比在底部
        try {
            PicPreviewJsRequest request = new Gson().fromJson(data, PicPreviewJsRequest.class);
            // 一行代码即可实现大部分需求，如需定制，可参考下面自定义的代码：
            ImagePreview.getInstance().setContext(getActivity())
                    .setEnableDragClose(true)//设置是否开启下拉图片退出
                    .setShowCloseButton(true)//设置是否显示关闭按钮
                    .setImageList(Arrays.asList(request.getImages())).start();
        } catch (Exception e) {
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
