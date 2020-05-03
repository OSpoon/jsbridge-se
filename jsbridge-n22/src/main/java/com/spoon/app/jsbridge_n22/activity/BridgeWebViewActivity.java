package com.spoon.app.jsbridge_n22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.base.BaseActivity;
import com.spoon.app.jsbridge_n22.core.BridgeWebView;

import static com.spoon.app.jsbridge_n22.core.extension.bean.UploadMessage.FILE_CHOOSER_RESULT_CODE;

public class BridgeWebViewActivity extends BaseActivity {

    private final static String ROOT_URL = "ROOT_URL";

    private BridgeWebView bridgeWebview;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, BridgeWebViewActivity.class);
        intent.putExtra(ROOT_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        bridgeWebview = findViewById(R.id.activity_bridge_webview);
        String url = getIntent().getStringExtra(ROOT_URL);
        if (!TextUtils.isEmpty(url)) {
            bridgeWebview.loadUrl(url);
        }
    }

    /**
     * 监听返回键的逻辑
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 返回键
            if (!canGoBack()) {
                //关闭页面
                closePage();
            } else {
                //返回前一个页面
                goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * 关闭页面
     */
    private void closePage() {
        if (this != null) {
            this.finish();
        }
    }

    /**
     * 检查是否可以返回历史记录中的一页，然后执行此操作。
     */
    public void goBack() {
        if (this.bridgeWebview != null && this.bridgeWebview.canGoBack()) {
            this.bridgeWebview.goBack();
        }
    }

    /**
     * web浏览器可以返回吗?
     *
     * @return boolean
     */
    public boolean canGoBack() {
        return this.bridgeWebview != null && this.bridgeWebview.canGoBack();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (bridgeWebview.getChromeClient() != null) {
                bridgeWebview.getChromeClient().getUploadMessage().onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}