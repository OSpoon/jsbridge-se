package com.spoon.app.jsbridge_n22.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.spoon.app.jsbridge_n22.R;

@SuppressLint("SetJavaScriptEnabled")
public class BridgeWebView extends WebView implements IWebView {

    private ProgressBar progressbar;


    private String TAG = "BridgeWebView";
    private BridgeTiny bridgeTiny;
    private BridgeWebViewClient mClient;
    private BridgeWebChromeClient mChromeClient;

    public BridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public BridgeWebView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        //添加进度条
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);

        clearCache(true);
        getSettings().setUseWideViewPort(true);
//		webView.getSettings().setLoadWithOverviewMode(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);//开启
//        mContent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //开启Http和Https混用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Bridge.INSTANCE.getDEBUG()) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        bridgeTiny = new BridgeTiny(this);

        mClient = new BridgeWebViewClient(this,bridgeTiny);
        super.setWebViewClient(mClient);
        mChromeClient = new BridgeWebChromeClient(progressbar);
        super.setWebChromeClient(mChromeClient);
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        mClient.setWebViewClient(client);
    }


    @Override
    public void destroy() {
        super.destroy();
        bridgeTiny.freeMemory();
    }

    @Override
    public void evaluateJavascript(String var1, Object object) {
        super.evaluateJavascript(var1, (ValueCallback<String>) object);
    }

    @Override
    public void callHandler(String handlerName, Object data, OnBridgeCallback responseCallback) {
        bridgeTiny.callHandler(handlerName,data,responseCallback);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
