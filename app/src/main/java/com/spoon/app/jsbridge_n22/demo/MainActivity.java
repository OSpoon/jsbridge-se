package com.spoon.app.jsbridge_n22.demo;

import android.os.Bundle;

import com.spoon.app.jsbridge_n22.activity.BridgeWebViewActivity;
import com.spoon.app.jsbridge_n22.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL);
    }
}
