package com.spoon.app.jsbridge_n22.demo;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.ospoon.app.sunlife.jsbridge_plugins_n22.Constants;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewActivity;
import com.spoon.app.jsbridge_n22.activity.X5WebViewActivity;

public class MainActivity extends Activity {

    MyBroadcastReceiver mMyBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL);
            }
        });
        findViewById(R.id.buttonx5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                X5WebViewActivity.start(MainActivity.this, AppContext.ROOT_URL);
            }
        });
        //注册广播用于接收Js通过插件Push到原生的数据
        mMyBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(mMyBroadcastReceiver, new IntentFilter(Constants.JSBRIDGEN22_JS_PUSH_DATA_ACTION));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMyBroadcastReceiver);
        super.onDestroy();
    }
}
