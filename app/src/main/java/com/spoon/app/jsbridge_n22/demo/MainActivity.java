package com.spoon.app.jsbridge_n22.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.spoon.app.jsbridge_n22.activity.BridgeWebViewActivity;

public class MainActivity extends Activity {

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
    }
}
