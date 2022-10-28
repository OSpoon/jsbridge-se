package com.spoon.app.jsbridge_se.base;

import android.app.Activity;
import android.content.Intent;

import com.spoon.app.jsbridge_se.bean.MessageEvent;
import com.spoon.app.jsbridge_se.core.BridgeLog;
import com.spoon.app.jsbridge_se.core.BridgeWebView;
import com.spoon.app.jsbridge_se.core.OnBridgeCallback;
import com.spoon.app.jsbridge_se.uiInterface.MAInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseActivity extends Activity {

    private MAInterface maInterface;
    private BridgeWebView bridgeWebView;
    private String parentActivityId;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (maInterface != null) {
            maInterface.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void setMaInterface(MAInterface maInterface) {
        this.maInterface = maInterface;
    }

    public void setBridgeWebView(BridgeWebView bridgeWebView) {
        this.bridgeWebView = bridgeWebView;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event != null) {
            if (event.getParentActivityId().equals(this.toString())) {
                if (bridgeWebView != null) {
                    bridgeWebView.callHandler(event.getHandlerName(), event.getData(), getResponseCallback("bridgeWebView"));
                }
            }
        }
    }

    protected OnBridgeCallback getResponseCallback(final String type) {
        return new OnBridgeCallback() {
            @Override
            public void onCallBack(String data) {
                BridgeLog.d(type, data);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public String getParentActivityId() {
        return parentActivityId;
    }

    public void setParentActivityId(String parentActivityId) {
        this.parentActivityId = parentActivityId;
    }

    public void reload() {
        if (bridgeWebView != null) {
            bridgeWebView.reload();
        }
    }
}
