package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.ShareWeChatJsRequest;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ShareUtils;
import com.spoon.app.jsbridge_n22.utils.Utils;

/**
 * 发送信息的方法
 *
 * @author gdk
 */
@BridgePlugin(name = "shareWeChat")
public class ShareWechatBridgeHandler extends BaseBridgeHandler {
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
        final ShareWeChatJsRequest reqest = new Gson().fromJson(data, ShareWeChatJsRequest.class);
        String rootUrl = "";
        if (reqest.getIconUrl().startsWith("http")) {
            rootUrl = reqest.getIconUrl();
        } else {
            rootUrl = Utils.getRootUrl(reqest.getIconUrl());
        }
        Glide.with(getActivity()).asBitmap().load(rootUrl).into(new SimpleTarget<Bitmap>() {
            /**
             * 成功的回调
             */
            @Override
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                // 下面这句代码是一个过度dialog，因为是获取网络图片，需要等待时间
                ShareUtils.shareWeb(getActivity(), reqest.getTitle(), reqest.getDesc(), bitmap,
                        reqest.getWebPageUrl(), reqest.getPlatform());
            }

            /**
             * 失败的回调
             */
            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                ShareUtils.shareWeb(getActivity(), reqest.getTitle(), reqest.getDesc(), null,
                        reqest.getWebPageUrl(), reqest.getPlatform());
            }
        });

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
