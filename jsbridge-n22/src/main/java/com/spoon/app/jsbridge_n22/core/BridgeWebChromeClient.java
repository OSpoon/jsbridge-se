package com.spoon.app.jsbridge_n22.core;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BridgeWebChromeClient extends WebChromeClient {

    private ProgressBar progressbar;

    public BridgeWebChromeClient(ProgressBar progressbar) {
        this.progressbar = progressbar;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress == 100) {
            progressbar.setVisibility(GONE);
        } else {
            if (progressbar.getVisibility() == GONE) {
                progressbar.setVisibility(VISIBLE);
            }
            progressbar.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }
}
