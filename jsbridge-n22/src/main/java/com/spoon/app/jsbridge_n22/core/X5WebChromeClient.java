package com.spoon.app.jsbridge_n22.core;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.ProgressBar;

import com.spoon.app.jsbridge_n22.core.extension.bean.X5UploadMessage;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * BridgeWebChromeClient github:https://github.com/shuhaoSCode/AndroidWebviewInputFile
 * <p>
 * 现改名使用X5WebChromeClient
 */
public class X5WebChromeClient extends WebChromeClient {
    static class BridgeWebChromeClientBuild {
        X5UploadMessage uploadMessage;
        ActivityCallBack callBack;
        ProgressBar progressbar;

        BridgeWebChromeClientBuild(ProgressBar progressbar, ActivityCallBack callBack) {
            this.uploadMessage = new X5UploadMessage();
            this.callBack = callBack;
            this.progressbar = progressbar;
        }

        public X5WebChromeClient build() {
            return new X5WebChromeClient(this);
        }
    }

    public static X5WebChromeClient createBuild(ProgressBar progressbar, ActivityCallBack callBack) {
        return new BridgeWebChromeClientBuild(progressbar, callBack).build();
    }

    BridgeWebChromeClientBuild build;

    private X5WebChromeClient(BridgeWebChromeClientBuild build) {
        this.build = build;
    }

    public interface ActivityCallBack {
        void FileChooserBack(Intent intent);
    }

    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        if (newProgress == 100) {
            build.progressbar.setVisibility(GONE);
        } else {
            if (build.progressbar.getVisibility() == GONE) {
                build.progressbar.setVisibility(VISIBLE);
            }
            build.progressbar.setProgress(newProgress);
        }
        super.onProgressChanged(webView, newProgress);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onShowFileChooser(WebView webView, com.tencent.smtt.sdk.ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        build.uploadMessage.setUploadMessageAboveL(valueCallback);
        build.callBack.FileChooserBack(build.uploadMessage.openImageChooserActivity(fileChooserParams.getAcceptTypes()));
        return true;
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
//        build.uploadMessage.setUploadMessageAboveL(filePathCallback);
//        build.callBack.FileChooserBack(build.uploadMessage.openImageChooserActivity(fileChooserParams.getAcceptTypes()));
//        return true;
//    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        //uploadMessage = valueCallback;
        build.uploadMessage.setUploadMessage(valueCallback);
        build.callBack.FileChooserBack(build.uploadMessage.openImageChooserActivity("image/*"));
    }

    // For Android  >= 3.0
    public void openFileChooser(ValueCallback valueCallback, String acceptType) {
        build.uploadMessage.setUploadMessage(valueCallback);
        build.callBack.FileChooserBack(build.uploadMessage.openImageChooserActivity(acceptType));
    }

    //For Android  >= 4.1
    public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
        build.uploadMessage.setUploadMessage(valueCallback);
        build.callBack.FileChooserBack(build.uploadMessage.openImageChooserActivity(acceptType));
    }

    public X5UploadMessage getUploadMessage() {
        return build.uploadMessage;
    }
}