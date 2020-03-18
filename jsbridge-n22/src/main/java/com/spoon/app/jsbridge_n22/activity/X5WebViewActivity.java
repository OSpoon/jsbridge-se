package com.spoon.app.jsbridge_n22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.base.BaseActivity;
import com.spoon.app.jsbridge_n22.core.BridgeWebView;
import com.spoon.app.jsbridge_n22.core.X5WebView;

public class X5WebViewActivity extends BaseActivity {

    private final static String ROOT_URL = "ROOT_URL";

    private X5WebView x5WebView;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, X5WebViewActivity.class);
        intent.putExtra(ROOT_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x5);
        x5WebView = findViewById(R.id.activity_x5_webview);
        String url = getIntent().getStringExtra(ROOT_URL);
        if (!TextUtils.isEmpty(url)) {
            x5WebView.loadUrl(url);
        }
    }

}