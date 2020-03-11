package com.spoon.app.jsbridge_n22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.base.BaseActivity;
import com.spoon.app.jsbridge_n22.core.BridgeWebView;

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

}