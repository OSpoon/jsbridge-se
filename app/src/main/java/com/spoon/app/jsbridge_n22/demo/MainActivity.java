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
import com.spoon.app.jsbridge_n22.bean.NavigationBarDataBean;

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
//                BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL);
//                BridgeWebViewActivity.start(MainActivity.this, "file:///android_asset/www/index.html#/personal/personalInfo");
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
                shareModelBean.setImageUrl("http://xinyidongzhanyeguangsubao-st-1254235118.cos.ap-beijing.myqcloud.com/PC/showConfig/shareSaleProduct/Share/2020-06-27/1593252755215_1589356124880_%E5%9B%BE%E5%B1%82%208%403x.png");
                shareModelBean.setShareTitle("我是分享页面的标题");
                shareModelBean.setShareDescription("光速宝3.0更新");
                shareModelBean.setShareUrl("https://www.baidu.com");
                navigationBarDataBean.setShareModel(shareModelBean);
//                BridgeWebViewActivity.start(MainActivity.this,
//                        "file:///android_asset/www/index.html#/personal/personalInfo", navigationBarDataBean);
//                BridgeWebViewActivity.start(MainActivity.this,
//                        "https://wdst.e-sleb.com/app.html#/myWechartShop?agentCode=mu5%2Bbi3QTyKogy8dba07bw%3D%3D&branchtype=01", null);
                BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL,navigationBarDataBean);

//                SPUtils instance = SPUtils.getInstance();
//                String userInfo = instance.getString("userInfo");
//                HashMap<String, Object> saveDatas = new HashMap<>();
//                Gson gson = new Gson();
//                UserInfoBean userInfoBean = gson.fromJson(userInfo, UserInfoBean.class);
//                saveDatas.put("userInfo", userInfoBean);
//                saveDatas.put("productName", "光大永明富运年年00000");
//                saveDatas.put("productCodeDetail", "HMT000");
//                saveDatas.put("pageResource", "000");
//                BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL, null,saveDatas);
//                BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL, null, "123", "光速宝");
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
