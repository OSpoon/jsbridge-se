package com.spoon.app.jsbridge_n22.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by gdk on 2020/03/25 17:26
 *
 * @author gdk
 */
public class ShareUtils {
    /**
     * @param context
     * @param title
     * @param content
     * @param imgBitmap
     * @param platform
     */
    public static void shareWeb(Context context, String title, String content, Bitmap imgBitmap, String webPageUrl,
                                String platform) {
        // 通过appId得到IWXAPI这个对象 todo appid 暂定写死,后修改为可配置
        String jsbridge_n22_wechat_share_key = Utils.getAppMetaKey(context, "JSBRIDGE_N22_WECHAT_SHARE_KEY");
        IWXAPI wxapi = WXAPIFactory.createWXAPI(context, jsbridge_n22_wechat_share_key);
        // 检查手机或者模拟器是否安装了微信
        if (!wxapi.isWXAppInstalled()) {
            Toast.makeText(context, "您还没有安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        // 初始化一个WXWebpageObject对象
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = webPageUrl;
        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        // 填写网页标题、描述、位图
        msg.title = title;
        msg.description = content;
        // 如果没有位图，可以传null，会显示默认的图片
        if (imgBitmap != null) {
            msg.setThumbImage(imgBitmap);
        }
        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // transaction用于唯一标识一个请求（可自定义）
        req.transaction = "webpage";
        // 上文的WXMediaMessage对象
        req.message = msg;
        //1.好友会话,2.朋友圈,3.收藏, 默认是好友会话
        if ("1".equals(platform)) {
            req.scene = SendMessageToWX.Req.WXSceneSession;//是分享到好友会话
        } else if ("2".equals(platform)) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;//是分享到朋友圈
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;//是分享到好友会话
        }
        wxapi.sendReq(req);
    }
}
