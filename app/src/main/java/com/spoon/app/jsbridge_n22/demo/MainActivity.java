package com.spoon.app.jsbridge_n22.demo;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.Constants;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.core.security.httpcore.TextUtils;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewActivity;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewCustomActivity;
import com.spoon.app.jsbridge_n22.activity.X5WebViewActivity;
import com.spoon.app.jsbridge_n22.activity.X5WebViewCustomActivity;
import com.spoon.app.jsbridge_n22.core.extension.bean.Options;
import com.spoon.app.jsbridge_n22.core.extension.bean.Title;
import com.spoon.app.jsbridge_n22.core.extension.bean.Toolbar;

public class MainActivity extends Activity {

    MyBroadcastReceiver mMyBroadcastReceiver;
    EditText edit_host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_host = findViewById(R.id.edit_host);

        findViewById(R.id.button_edit_host).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String host = edit_host.getText().toString();
                if (!TextUtils.isEmpty(host)) {
                    BridgeWebViewActivity.start(MainActivity.this, host);
                } else {
                    Toast.makeText(MainActivity.this, "请填写访问地址", Toast.LENGTH_SHORT).show();
                }

            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Options options = new Options();

                Toolbar toolbar = new Toolbar();
                toolbar.height = 44;
                toolbar.color = "#f0f0f0ff";
                options.toolbar = toolbar;

                Title title = new Title();
                title.color = "#003264ff";
                title.staticText = "美好的一天";
                options.title = title;

                options.isShowClose = false;
                options.isShowBack = true;
                options.isShowShare = true;

                BridgeWebViewCustomActivity.start(MainActivity.this, AppContext.ROOT_URL, options);
            }
        });
        findViewById(R.id.buttonx5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                X5WebViewActivity.start(MainActivity.this, AppContext.ROOT_URL);
            }
        });

        findViewById(R.id.button2x5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Options options = new Options();

                Toolbar toolbar = new Toolbar();
                toolbar.height = 44;
                toolbar.color = "#f0f0f0ff";
                options.toolbar = toolbar;

                Title title = new Title();
                title.color = "#003264ff";
                title.staticText = "美好的一天";
                options.title = title;

                options.isShowClose = false;
                options.isShowBack = true;
                options.isShowShare = true;

                X5WebViewCustomActivity.start(MainActivity.this, AppContext.ROOT_URL, options);
            }
        });
        //注册广播用于接收Js通过插件Push到原生的数据
        mMyBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(mMyBroadcastReceiver, new IntentFilter(Constants.JSBRIDGEN22_JS_PUSH_DATA_ACTION));

        SPUtils instance = SPUtils.getInstance();
        String resourceFromAssets = ResourceUtils.readAssets2String("userInfo.json");
        instance.put("userInfo", resourceFromAssets);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMyBroadcastReceiver);
        super.onDestroy();
    }
}
