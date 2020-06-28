package com.spoon.app.jsbridge_n22.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.base.BaseActivity;
import com.spoon.app.jsbridge_n22.utils.LoadingDialog;

import java.util.Map;

import static com.spoon.app.jsbridge_n22.core.extension.bean.UploadMessage.FILE_CHOOSER_RESULT_CODE;

@SuppressLint("SetJavaScriptEnabled")
public class BridgeWebView extends WebView implements IWebView {


    private ProgressBar progressbar;

    private WebViewLoadListener listener;

    private String TAG = "BridgeWebView";
    private BridgeTiny bridgeTiny;
    private BridgeWebViewClient mClient;
    private BridgeWebChromeClient mChromeClient;
    private LoadingDialog loadingDialog;


    public BridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public BridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public BridgeWebView(Context context, WebViewLoadListener listener) {
        super(context);
        this.listener = listener;
        init(context);
    }

    public BridgeWebView(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        //添加进度条
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        progressbar.setVisibility(GONE);
        addView(progressbar);

        loadingDialog = new LoadingDialog(context);

        clearCache(true);
        getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = context.getApplicationContext().getCacheDir().getAbsolutePath();
        getSettings().setAppCachePath(appCachePath);
//        getSettings().setAllowFileAccess(true);    // 可以读取文件缓存
//        getSettings().setAppCacheEnabled(true);    //开启H5(APPCache)缓存功能

        getSettings().setUseWideViewPort(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);//开启

//        mContent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //开启Http和Https混用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Bridge.INSTANCE.getDEBUG()) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        bridgeTiny = new BridgeTiny(this);
        mClient = new BridgeWebViewClient(this, bridgeTiny, listener);
        super.setWebViewClient(mClient);
        mChromeClient = BridgeWebChromeClient.createBuild(progressbar, loadingDialog,
                new BridgeWebChromeClient.ActivityCallBack() {
                    @Override
                    public void FileChooserBack(Intent intent) {
                        try {
                            ((BaseActivity) context).startActivityForResult(intent, FILE_CHOOSER_RESULT_CODE);
                        } catch (Exception e) {
                            Log.e("BridgeWebView", "类型转换出现异常,使用webview的activity需要继承自BaseActivity");
                        }
                    }
                });
        super.setWebChromeClient(mChromeClient);
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        mClient.setWebViewClient(client);
    }

    @Override
    public void destroy() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        super.destroy();
        bridgeTiny.freeMemory();
    }

    @Override
    public void evaluateJavascript(String var1, Object object) {
        super.evaluateJavascript(var1, (ValueCallback<String>) object);
    }

    @Override
    public void callHandler(String handlerName, Object data, OnBridgeCallback responseCallback) {
        bridgeTiny.callHandler(handlerName, data, responseCallback);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public BridgeWebChromeClient getChromeClient() {
        return mChromeClient;
    }

}
