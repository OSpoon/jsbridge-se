package com.spoon.app.jsbridge_n22.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.adapter.NavigationBarAdapter;
import com.spoon.app.jsbridge_n22.base.BaseActivity;
import com.spoon.app.jsbridge_n22.bean.NavigationBarDataBean;
import com.spoon.app.jsbridge_n22.core.BridgeWebView;
import com.spoon.app.jsbridge_n22.core.extension.bean.Options;

import java.util.Collections;
import java.util.List;

import static com.spoon.app.jsbridge_n22.core.extension.bean.UploadMessage.FILE_CHOOSER_RESULT_CODE;

/**
 * @author thinkpad
 */
public class BridgeWebViewActivity extends BaseActivity implements View.OnClickListener {

    private final static String ROOT_URL = "ROOT_URL";
    /**
     * 左侧/右侧图标和中间标题
     */
    private RelativeLayout rl_title_bar;
    /**
     * 左侧图标
     */
    private ImageView iv_left_icon;
    /*右侧图标*/
    private ImageView iv_rightIco;
    /* 中间标题*/
    private TextView tv_title_middle;
    /* 左侧关闭按钮标题*/
    private TextView tvClose;
    /* 左侧关闭按钮标题*/
    private RecyclerView recyclerViewFunction;
    private NavigationBarAdapter navigationBarAdapter;
    private List<NavigationBarDataBean.NavigationBarBean.ChangeRightImageBean> changeRightImageBeanList;

    private BridgeWebView bridgeWebview;
    private String url;
    private final static String OPTIONS = "OPTIONS";
    private Options options;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, BridgeWebViewActivity.class);
        intent.putExtra(ROOT_URL, url);
        context.startActivity(intent);
    }

    public static void start(Context context, String url, Options options) {
        Intent intent = new Intent(context, BridgeWebViewActivity.class);
        intent.putExtra(ROOT_URL, url);
        intent.putExtra(OPTIONS, options);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        url = getIntent().getStringExtra(ROOT_URL);
        options = (Options) getIntent().getSerializableExtra(OPTIONS);
        //获取用户信息并且设置cookie
        getUserInfo();
        initView();
        setNavigationBarData(options);
        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        bridgeWebview = findViewById(R.id.activity_bridge_webview);
        rl_title_bar = findViewById(R.id.rl_title_bar);
        tv_title_middle = findViewById(R.id.tv_title_middle);
        iv_left_icon = findViewById(R.id.iv_back);
        tvClose = findViewById(R.id.tv_close);
        iv_rightIco = findViewById(R.id.iv_right);
        recyclerViewFunction = findViewById(R.id.recyclerView_function);

        //图标列表
        GridLayoutManager layoutManagerFunction = new GridLayoutManager(this, 4);
        navigationBarAdapter = new NavigationBarAdapter(R.layout.item_navigation_function, this);
        recyclerViewFunction.setLayoutManager(layoutManagerFunction);
        recyclerViewFunction.setAdapter(navigationBarAdapter);

        tvClose.setOnClickListener(this);
        iv_rightIco.setOnClickListener(this);
        iv_left_icon.setOnClickListener(this);
    }

    /**
     * 设置标题栏数据
     *
     * @param options
     */
    private void setNavigationBarData(Options options) {
        if (options != null) {
            //判断是否显示分享按钮
            if (options.isShowShare()) {
                iv_rightIco.setVisibility(View.VISIBLE);
            }
        }

    }

    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        //获取用户信息
        SPUtils instance = SPUtils.getInstance();
        String userInfo = instance.getString("userInfo");
//        UserInfoBean userInfoBean = new Gson().fromJson(userInfo, UserInfoBean.class);
//        Log.e("tag", "onCreate: " + userInfoBean.getToken());
//        CookieUtils.synCookies(url, userInfoBean.getToken(), this);
    }


    /**
     * 初始化数据
     */
    private void initData() {
        if (!TextUtils.isEmpty(url)) {
            bridgeWebview.loadUrl(url);
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.iv_right:
//
//                break;
//            case R.id.tv_close:
//
//                break;
            default:
                break;
        }
    }

    /**
     *
     * @param navigationBarDataBean
     */
    public void setTitleBar(NavigationBarDataBean navigationBarDataBean) {
        if (navigationBarDataBean != null) {
            changeRightImageBeanList = navigationBarDataBean.getNavigationBar().getChangeRightImage();
            Collections.sort(changeRightImageBeanList);
            navigationBarAdapter.setNewData(changeRightImageBeanList);
        }
    }
    /**
     * 监听返回键的逻辑
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 返回键
            if (!canGoBack()) {
                //关闭页面
                closePage();
            } else {
                //返回前一个页面
                goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * 关闭页面
     */
    private void closePage() {
        if (this != null) {
            this.finish();
        }
    }

    /**
     * 检查是否可以返回历史记录中的一页，然后执行此操作。
     */
    public void goBack() {
        if (this.bridgeWebview != null && this.bridgeWebview.canGoBack()) {
            this.bridgeWebview.goBack();
        }
    }

    /**
     * web浏览器可以返回吗?
     *
     * @return boolean
     */
    public boolean canGoBack() {
        return this.bridgeWebview != null && this.bridgeWebview.canGoBack();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (bridgeWebview.getChromeClient() != null) {
                bridgeWebview.getChromeClient().getUploadMessage().onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}