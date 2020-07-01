package com.spoon.app.jsbridge_n22.demo.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.spoon.app.jsbridge_n22.bean.MessageEvent;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;


/**
 * 微信分享集成页面
 *
 * @author gdk
 * @date 2020/03/26
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI wxapi;
    //    public static final String wx_appid = "wxba50597b5a9c762d";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        wxapi.handleIntent(intent, this);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String wx_appid = getAppMetaKey(this);
        wxapi = WXAPIFactory.createWXAPI(this, "appId");
        wxapi.registerApp(wx_appid);
        wxapi.handleIntent(getIntent(), this);
    }

    /**
     * 微信发送请求到第三方应用时，会回调到该方法
     */
    @Override
    public void onReq(BaseReq baseReq) {
        // 这里不作深究
    }

    /**
     * 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
     * app发送消息给微信，处理返回消息的回调
     */
    @Override
    public void onResp(BaseResp baseResp) {

        switch (baseResp.errCode) {
            // 正确返回
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    // ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX是微信分享，api自带
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                        // 只是做了简单的finish操作
                        Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new MessageEvent("分享成功"));
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            default:
                // 错误返回
                switch (baseResp.getType()) {
                    // 微信分享
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                        Toast.makeText(this, "分享失败", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new MessageEvent("分享失败"));
                        finish();
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    private String getAppMetaKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                // MY_META_KEY是meta-data中对应的key值
                appKey = metaData.getString("JSBRIDGE_N22_WECHAT_SHARE_KEY");
                return appKey;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return appKey;
    }

}
