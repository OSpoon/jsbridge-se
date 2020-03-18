package com.spoon.app.jsbridge_n22.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

import com.spoon.app.jsbridge_n22.R;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * author : zhangxin
 * date : 2020-03-18 17:15
 * description :
 */
public class X5WebView extends WebView implements IWebView {

    private ProgressBar progressbar;
    private BridgeTiny bridgeTiny;

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        this.getView().setClickable(true);
    }

    private void init(Context context) {
        //添加进度条
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.MATCH_PARENT, 5, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);

        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计

        bridgeTiny = new BridgeTiny(this);

        super.setWebViewClient(client);
        super.setWebChromeClient(ChromeClient);
    }

    @Override
    public void destroy() {
        super.destroy();
        bridgeTiny.freeMemory();
    }

    @Override
    public void evaluateJavascript(String var1, Object object) {
        if(object == null){
            super.evaluateJavascript(var1, null);
            return;
        }
        super.evaluateJavascript(var1, (ValueCallback<String>) object);
    }

    @Override
    public void callHandler(String handlerName, Object data, OnBridgeCallback responseCallback) {
        bridgeTiny.callHandler(handlerName,data,responseCallback);
    }

    private WebChromeClient ChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE) {
                    progressbar.setVisibility(VISIBLE);
                }
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(webView, newProgress);
        }
    };

    private WebViewClient client = new WebViewClient() {
        /**
         * prevent system browser from launching when web page loads
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            bridgeTiny.webViewLoadJs((IWebView) webView);
        }

    };
}
