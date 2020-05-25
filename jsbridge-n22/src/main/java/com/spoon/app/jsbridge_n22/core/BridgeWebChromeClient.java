package com.spoon.app.jsbridge_n22.core;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.spoon.app.jsbridge_n22.core.extension.bean.UploadMessage;
import com.spoon.app.jsbridge_n22.utils.LoadingDialog;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * BridgeWebChromeClient github:https://github.com/shuhaoSCode/AndroidWebviewInputFile
 * <p>
 * 现改名使用BridgeWebChromeClient
 */
public class BridgeWebChromeClient extends WebChromeClient {
    static class BridgeWebChromeClientBuild {
        UploadMessage uploadMessage;
        ActivityCallBack callBack;
        ProgressBar progressbar;
        LoadingDialog loadingDialog;

        BridgeWebChromeClientBuild(ProgressBar progressbar, LoadingDialog loadingDialog, ActivityCallBack callBack) {
            this.uploadMessage = new UploadMessage();
            this.callBack = callBack;
            this.progressbar = progressbar;
            this.loadingDialog = loadingDialog;
        }

        public BridgeWebChromeClient build() {
            return new BridgeWebChromeClient(this);
        }

    }

    public static BridgeWebChromeClient createBuild(ProgressBar progressbar, LoadingDialog loadingDialog, ActivityCallBack callBack) {
        return new BridgeWebChromeClientBuild(progressbar, loadingDialog, callBack).build();
    }

    BridgeWebChromeClientBuild build;

    private BridgeWebChromeClient(BridgeWebChromeClientBuild build) {
        this.build = build;
    }

    public interface ActivityCallBack {
        void FileChooserBack(Intent intent);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress == 100) {
            build.progressbar.setVisibility(GONE);
            if (build.loadingDialog != null) {
                build.loadingDialog.dismiss();
            }
        } else {
            if (build.loadingDialog != null) {
                build.loadingDialog.show();
            }
            if (build.progressbar.getVisibility() == GONE) {
                build.progressbar.setVisibility(GONE);
            }
            build.progressbar.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        build.uploadMessage.setUploadMessageAboveL(filePathCallback);
        build.callBack.FileChooserBack(build.uploadMessage.openImageChooserActivity(fileChooserParams.getAcceptTypes()));
        return true;
    }

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

    public UploadMessage getUploadMessage() {
        return build.uploadMessage;
    }
}