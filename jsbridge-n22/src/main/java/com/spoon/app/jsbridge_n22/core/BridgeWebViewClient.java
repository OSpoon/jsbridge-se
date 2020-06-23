package com.spoon.app.jsbridge_n22.core;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.SPUtils;
import com.spoon.app.jsbridge_n22.utils.CookieUtils;
import com.spoon.app.jsbridge_n22.utils.GsonUtils;

import java.util.Map;


/**
 * 如果要自定义WebViewClient必须要集成此类
 * Created by bruce on 10/28/15.
 */
class BridgeWebViewClient extends WebViewClient {

    private WebViewClient mClient;
    private BridgeTiny bridgeTiny;
    private BridgeWebView bridgeWebView;
    private WebViewLoadListener listener;
    private Map<String, Object> saveDatas;

    public BridgeWebViewClient(BridgeWebView bridgeWebView, BridgeTiny bridgeTiny, WebViewLoadListener listener) {
        this.bridgeTiny = bridgeTiny;
        this.bridgeWebView = bridgeWebView;
        this.listener = listener;
    }

    public void setWebViewClient(WebViewClient client) {
        mClient = client;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith("gap:") || url.startsWith("gap_init:")) {
            Log.i("BridgeWebView", "BridgeWebView does not support Cordova API calls:" + url);
            return true;
        }
        view.loadUrl(url);
        if (mClient != null) {
            return mClient.shouldOverrideUrlLoading(view, url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if ("ready".equals(request.getUrl().getAuthority())) {
                Log.i("BridgeWebView", "BridgeWebView does not support Cordova API calls:" + request.getUrl().getAuthority());
                return true;
            }
            view.loadUrl(request.getUrl().getAuthority());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && mClient != null) {
            return mClient.shouldOverrideUrlLoading(view, request);
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mClient != null) {
            mClient.onPageStarted(view, url, favicon);
        } else {
            super.onPageStarted(view, url, favicon);
        }
        if (saveDatas != null) {
            for (Map.Entry<String, Object> saveData : saveDatas.entrySet()) {
                System.out.println("key = " + saveData.getKey() + ", value = " + saveData.getValue());
                String json = GsonUtils.toJson(saveData.getValue());
                if (!TextUtils.isEmpty(json)) {
                    CookieUtils.localStorageData(view, saveData.getKey(), json);
                }
            }
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {

        if (listener != null) {
            listener.onPageFinished(url, view.canGoBack(),
                    view.canGoForward());
        }
        if (mClient != null) {
            mClient.onPageFinished(view, url);
        } else {
            super.onPageFinished(view, url);
        }
//        //向页面传输localStorage
//        CookieUtils.localStorageData(bridgeWebView, SPUtils.getInstance().getString("productName"),
//                SPUtils.getInstance().getString("productCodeDetail"), SPUtils.getInstance().
//                        getString("pageResource"));

        bridgeTiny.webViewLoadJs(bridgeWebView);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        if (mClient != null) {
            mClient.onLoadResource(view, url);
        } else {
            super.onLoadResource(view, url);
        }
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mClient != null) {
            mClient.onPageCommitVisible(view, url);
        } else {
            super.onPageCommitVisible(view, url);
        }
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (mClient != null) {
            return mClient.shouldInterceptRequest(view, url);
        }
        return super.shouldInterceptRequest(view, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && mClient != null) {
            return mClient.shouldInterceptRequest(view, request);
        }
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onTooManyRedirects(WebView view, android.os.Message cancelMsg, android.os.Message continueMsg) {
        if (mClient != null) {
            mClient.onTooManyRedirects(view, cancelMsg, continueMsg);
        } else {
            super.onTooManyRedirects(view, cancelMsg, continueMsg);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if (mClient != null) {
            mClient.onReceivedError(view, errorCode, description, failingUrl);
        } else {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mClient != null) {
            mClient.onReceivedError(view, request, error);
        } else {
            super.onReceivedError(view, request, error);
        }
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mClient != null) {
            mClient.onReceivedHttpError(view, request, errorResponse);
        } else {
            super.onReceivedHttpError(view, request, errorResponse);
        }

    }

    @Override
    public void onFormResubmission(WebView view, android.os.Message dontResend, android.os.Message resend) {
        if (mClient != null) {
            mClient.onFormResubmission(view, dontResend, resend);
        } else {
            super.onFormResubmission(view, dontResend, resend);
        }

    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        if (mClient != null) {
            mClient.doUpdateVisitedHistory(view, url, isReload);
        } else {
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        if (mClient != null) {
            mClient.onReceivedSslError(view, handler, error);
        } else {
            super.onReceivedSslError(view, handler, error);
        }
    }

    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && mClient != null) {
            mClient.onReceivedClientCertRequest(view, request);
        } else {
            super.onReceivedClientCertRequest(view, request);
        }
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        if (mClient != null) {
            mClient.onReceivedHttpAuthRequest(view, handler, host, realm);
        } else {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }
    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        if (mClient != null) {
            return mClient.shouldOverrideKeyEvent(view, event);
        }
        return super.shouldOverrideKeyEvent(view, event);
    }

    @Override
    public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
        if (mClient != null) {
            mClient.onUnhandledKeyEvent(view, event);
        } else {
            super.onUnhandledKeyEvent(view, event);
        }
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        if (mClient != null) {
            mClient.onScaleChanged(view, oldScale, newScale);
        } else {
            super.onScaleChanged(view, oldScale, newScale);
        }
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
        if (mClient != null) {
            mClient.onReceivedLoginRequest(view, realm, account, args);
        } else {
            super.onReceivedLoginRequest(view, realm, account, args);
        }
    }

    @Override
    public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mClient != null) {
            return mClient.onRenderProcessGone(view, detail);
        }
        return super.onRenderProcessGone(view, detail);
    }

    @Override
    public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 && mClient != null) {
            mClient.onSafeBrowsingHit(view, request, threatType, callback);
        } else {
            super.onSafeBrowsingHit(view, request, threatType, callback);
        }
    }

    public void setLocalStorage(Map<String, Object> saveDatas) {
        this.saveDatas = saveDatas;
    }
}