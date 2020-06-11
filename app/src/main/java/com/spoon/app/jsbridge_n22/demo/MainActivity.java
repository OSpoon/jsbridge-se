package com.spoon.app.jsbridge_n22.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.Constants;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.core.security.httpcore.TextUtils;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewActivity;
import com.spoon.app.jsbridge_n22.activity.BridgeWebViewCustomActivity;
import com.spoon.app.jsbridge_n22.activity.X5WebViewActivity;
import com.spoon.app.jsbridge_n22.activity.X5WebViewCustomActivity;
import com.spoon.app.jsbridge_n22.bean.NavigationBarDataBean;
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
                BridgeWebViewActivity.start(MainActivity.this, "file:///android_asset/www/index.html#/personal/personalInfo");
                NavigationBarDataBean navigationBarDataBean = new NavigationBarDataBean();
                NavigationBarDataBean.NavigationBarBean navigationBarBean = new NavigationBarDataBean.NavigationBarBean();
                navigationBarDataBean.setIsShowShare("1");
                navigationBarDataBean.setIsShowNavigationBar("1");
                navigationBarBean.setIsShowClose("1");
                navigationBarBean.setIsShowTitle("1");
                navigationBarBean.setTitle("我是个人中心");
                navigationBarBean.setTitleColor("#FC7912");
                navigationBarBean.setTitleSize("15");

                navigationBarDataBean.setNavigationBar(navigationBarBean);
                NavigationBarDataBean.ShareModelBean shareModelBean = new NavigationBarDataBean.ShareModelBean();
                shareModelBean.setImageUrl("https://xinyidongzhanyeguangsubao-st-1254235118.cos.ap-beijing.myqcloud.com/PC/showConfig/hotSaleProduct/Show/2020-05-15/1589523629974_41586340874_.pic_hd.jpg");
                shareModelBean.setShareTitle("我是分享页面的标题");
                shareModelBean.setShareDescription("光速宝3.0更新");
                shareModelBean.setShareUrl("https://www.baidu.com");
                navigationBarDataBean.setShareModel(shareModelBean);
//                BridgeWebViewActivity.start(MainActivity.this,
//                        "file:///android_asset/www/index.html#/personal/personalInfo", navigationBarDataBean);
//                BridgeWebViewActivity.start(MainActivity.this,
//                        "https://wd.e-sleb.com/app.html#/myWechartShop?agentCode=KgG%2FSdkIzMS6eIP6i0mA2w%3D%3D&branchtype=01", null);
                BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL,navigationBarDataBean);
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
//                X5WebViewActivity.start(MainActivity.this, AppContext.ROOT_URL);
                X5WebViewActivity.start(MainActivity.this, "https://wd.e-sleb.com/app.html#/myWechartShop?agentCode=mu5%2Bbi3QTyKogy8dba07bw%3D%3D&branchtype=01");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode) {
            //判断跳转页面是否返回到首页
            if (requestCode == 2) {
                Toast.makeText(this, "我是调回来的页面", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
