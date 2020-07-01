package com.spoon.app.jsbridge_n22.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.adapter.NavigationBarAdapter;
import com.spoon.app.jsbridge_n22.base.BaseActivity;
import com.spoon.app.jsbridge_n22.bean.MessageEvent;
import com.spoon.app.jsbridge_n22.bean.NavigationBarDataBean;
import com.spoon.app.jsbridge_n22.bean.UserInfoBean;
import com.spoon.app.jsbridge_n22.core.BridgeWebView;
import com.spoon.app.jsbridge_n22.utils.CookieUtils;
import com.spoon.app.jsbridge_n22.utils.ShareUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.spoon.app.jsbridge_n22.core.extension.bean.UploadMessage.FILE_CHOOSER_RESULT_CODE;

/**
 * @author thinkpad
 */
public class BridgeWebViewActivity extends BaseActivity implements View.OnClickListener {
    MyBroadcastReceiver mMyBroadcastReceiver;

    private final static String ROOT_URL = "ROOT_URL";
    /**
     * 左侧/右侧图标和中间标题
     */
    private RelativeLayout rlTitleBarWeb;
    /**
     * 左侧图标
     */
    private ImageView ivLeftWeb;
    /*右侧图标*/
    private ImageView ivRightWeb;
    /* 中间标题*/
    private TextView tvTitleContentWeb;
    /* 左侧关闭按钮标题*/
    private ImageView ivCloseWeb;
    /* 左侧关闭按钮标题*/
    private RecyclerView recyclerViewFunction;
    private NavigationBarAdapter navigationBarAdapter;
    private List<NavigationBarDataBean.NavigationBarBean.ChangeRightImageBean> changeRightImageBeanList;

    private BridgeWebView bridgeWebview;
    private String url;
    private String productName;
    private String productCodeDetail;
    private final static String DATA = "data";
    private final static String ACTIVITY_ID = "activity_id";
    private final static String PRODUCT_NAME = "productName";
    private final static String PRODUCT_CODE_DETAIL = "productCodeDetail";
    private final static String LOCAL_STORAGE = "local_storage";
    private NavigationBarDataBean navigationBarDataBean;

    /**
     * 正常跳转
     *
     * @param activity：上下文
     * @param url：跳转链接
     */
    public static void start(Activity activity, String url) {
        Intent intent = new Intent(activity, BridgeWebViewActivity.class);
        intent.putExtra(ROOT_URL, url);
        intent.putExtra(ACTIVITY_ID, activity.toString());
        activity.startActivity(intent);
    }

    /**
     * 显示原生的标题栏信息
     *
     * @param activity：当前的activity
     * @param url：跳转地址
     * @param navigationBarDataBean：标题栏信息
     */
    public static void start(Activity activity, String url,
                             NavigationBarDataBean navigationBarDataBean) {
        Intent intent = new Intent(activity, BridgeWebViewActivity.class);
        intent.putExtra(ROOT_URL, url);
        intent.putExtra(DATA, navigationBarDataBean);
        intent.putExtra(ACTIVITY_ID, activity.toString());
        activity.startActivity(intent);
    }

    /**
     * 显示原生的标题栏信息
     *
     * @param activity：当前的activity
     * @param url：跳转地址
     * @param productName：产品名称
     * @param productCodeDetail：产品CODE
     */
    public static void start(Activity activity, String url,
                             NavigationBarDataBean navigationBarDataBean,
                             String productName, String productCodeDetail) {
        Intent intent = new Intent(activity, BridgeWebViewActivity.class);
        intent.putExtra(ROOT_URL, url);
        intent.putExtra(DATA, navigationBarDataBean);
        intent.putExtra(PRODUCT_NAME, productName);
        intent.putExtra(PRODUCT_CODE_DETAIL, productCodeDetail);
        intent.putExtra(ACTIVITY_ID, activity.toString());
        activity.startActivity(intent);
    }

    /**
     * @param activity：上下文
     * @param url：跳转链接
     */
    public static void start(Activity activity, String url, NavigationBarDataBean navigationBarDataBean,
                             HashMap<String, Object> saveDatas) {
        Intent intent = new Intent(activity, BridgeWebViewActivity.class);
        intent.putExtra(ROOT_URL, url);
        intent.putExtra(DATA, navigationBarDataBean);
        intent.putExtra(ACTIVITY_ID, activity.toString());
        intent.putExtra(LOCAL_STORAGE, saveDatas);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        url = getIntent().getStringExtra(ROOT_URL);
        navigationBarDataBean = (NavigationBarDataBean) getIntent().getSerializableExtra(DATA);
        productName = getIntent().getStringExtra(PRODUCT_NAME);
        productCodeDetail = getIntent().getStringExtra(PRODUCT_CODE_DETAIL);
        //获取用户信息并且设置cookie
        getUserInfo();
        getProductInfo(productName, productCodeDetail);
        initView();
        initBrocast();
        setNavigationBarData(navigationBarDataBean);
        initData();
    }


    /**
     * 注册广播
     */
    private void initBrocast() {
        //注册广播用于接收Js通过插件Push到原生的数据
        mMyBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(mMyBroadcastReceiver, new IntentFilter("com.n22.jsbridge.JS_TITLE_BAR_DATA"));

    }

    /**
     * 初始化控件
     */
    private void initView() {
        bridgeWebview = findViewById(R.id.activity_bridge_webview);
        super.setBridgeWebView(bridgeWebview);
        setParentActivityId(getIntent().getStringExtra(ACTIVITY_ID));
        rlTitleBarWeb = findViewById(R.id.rl_title_bar_web);
        tvTitleContentWeb = findViewById(R.id.tv_title_middle_web);
        ivLeftWeb = findViewById(R.id.iv_back_web);
        ivCloseWeb = findViewById(R.id.iv_close_web);
        ivRightWeb = findViewById(R.id.iv_right_web);
        recyclerViewFunction = findViewById(R.id.recyclerView_function);

        //图标列表
        GridLayoutManager layoutManagerFunction = new GridLayoutManager(this, 4);
        navigationBarAdapter = new NavigationBarAdapter(R.layout.item_navigation_function, this);
        recyclerViewFunction.setLayoutManager(layoutManagerFunction);
        recyclerViewFunction.setAdapter(navigationBarAdapter);
        //点击调用网页端的方法
        navigationBarAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                List data = adapter.getData(); todo
            }
        });
        ivCloseWeb.setOnClickListener(this);
        ivRightWeb.setOnClickListener(this);
        ivLeftWeb.setOnClickListener(this);
    }

    /**
     * 设置标题栏数据
     *
     * @param navigationBarData:
     */
    private void setNavigationBarData(NavigationBarDataBean navigationBarData) {
        if (navigationBarData != null) {
            //是否显示标题栏
            if ("0".equals(navigationBarData.getIsShowNavigationBar())) {
                rlTitleBarWeb.setVisibility(View.GONE);
            } else {
                rlTitleBarWeb.setVisibility(View.VISIBLE);
                //设置左侧返回图片的样式 0代表黄色返回按钮（默认），1代表黑色返回按钮，2灰色返回按钮，3代表白色按钮
                setLeftOrCloseImage(ivLeftWeb, ivCloseWeb, navigationBarData.getNavigationBar().getChangeLeftImage());
                //是否显示关闭按钮
                if ("0".equals(navigationBarData.getNavigationBar().getIsShowClose())) {
                    ivCloseWeb.setVisibility(View.GONE);
                } else {
                    ivCloseWeb.setVisibility(View.VISIBLE);
                }
                //是否显示分享按钮 0是隐藏，1是显示
                if ("0".equals(navigationBarData.getIsShowShare())) {
                    ivRightWeb.setVisibility(View.GONE);
                } else {
                    ivRightWeb.setVisibility(View.VISIBLE);
                }
                //是否显示标题
                if ("0".equals(navigationBarData.getNavigationBar().getIsShowTitle())) {
                    tvTitleContentWeb.setVisibility(View.GONE);
                } else {
                    tvTitleContentWeb.setVisibility(View.VISIBLE);
                    //设置标题
                    tvTitleContentWeb.setText(navigationBarData.getNavigationBar().getTitle());
                    //标题显示的颜色
                    if (!TextUtils.isEmpty(navigationBarData.getNavigationBar().getTitleColor())) {
                        tvTitleContentWeb.setTextColor(Color.parseColor(navigationBarData.
                                getNavigationBar().getTitleColor()));
                    }
                    //标题显示的字体的大小字号
                    if (!TextUtils.isEmpty(navigationBarData.getNavigationBar().getTitleSize())) {
                        tvTitleContentWeb.setTextSize(Float.valueOf(navigationBarData.
                                getNavigationBar().getTitleSize()));
                    }
                }

                if (navigationBarData.getNavigationBar().getChangeRightImage() != null) {
                    changeRightImageBeanList = navigationBarData.getNavigationBar().getChangeRightImage();
                    if (changeRightImageBeanList != null && changeRightImageBeanList.size() > 0) {
                        Collections.sort(changeRightImageBeanList);
                    }
                    navigationBarAdapter.setNewData(changeRightImageBeanList);
                }

            }
        }
    }

    /**
     * 设置返回和关闭按钮的图片
     *
     * @param ivLeft:左边的返回按钮
     * @param ivClose：左边的关闭按钮
     * @param changeLeftImage：设置图片的标志
     */
    private void setLeftOrCloseImage(ImageView ivLeft, ImageView ivClose, String changeLeftImage) {
        switch (changeLeftImage) {
            case "0":
                ivLeft.setImageResource(R.drawable.img_common_back_yellow);
                ivClose.setImageResource(R.drawable.img_common_close_yellow);
                break;
            case "1":
                ivLeft.setImageResource(R.drawable.img_common_back_black);
                ivClose.setImageResource(R.drawable.img_common_close_black);
                break;
            case "2":
                ivLeft.setImageResource(R.drawable.img_common_back_gray);
                ivClose.setImageResource(R.drawable.img_common_close_gray);
                break;
            case "3":
                ivLeft.setImageResource(R.drawable.img_common_back_white);
                ivClose.setImageResource(R.drawable.img_common_close_white);
                break;
            default:
                break;
        }
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        //获取用户信息
        SPUtils instance = SPUtils.getInstance();
        String userInfo = instance.getString("userInfo");
        UserInfoBean userInfoBean = new Gson().fromJson(userInfo, UserInfoBean.class);
        Log.e("tag", "onCreate: " + userInfoBean.getToken());
        CookieUtils.synCookies(url, userInfoBean.getToken(), this);
    }

    /**
     * 获取产品信息
     *
     * @param productName:产品名称
     * @param productCodeDetail：产品CODE
     */
    private void getProductInfo(String productName, String productCodeDetail) {
        SPUtils.getInstance().remove("productName");
        SPUtils.getInstance().remove("productCodeDetail");
        SPUtils.getInstance().remove("pageResource");

        if (!TextUtils.isEmpty(productName) && !TextUtils.isEmpty(productCodeDetail)) {
            CookieUtils.localStorageSaveData(productName, productCodeDetail);
        }
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
        int id = view.getId();
        if (id == R.id.iv_right_web) { //点击右边的分享按钮
            shareInfo(navigationBarDataBean);
        } else if (id == R.id.iv_close_web) { //点击关闭按钮
            //关闭按钮
            closePage();
        } else if (id == R.id.iv_back_web) { //点击左侧的返回键
            if (!canGoBack()) {
                //关闭页面
                closePage();
            } else {
                //返回前一个页面
                goBack();
            }
        }
    }

    /**
     * 点击分享按钮的弹框
     *
     * @param navigationBarDataBean:
     */
    private void shareInfo(final NavigationBarDataBean navigationBarDataBean) {
        if (navigationBarDataBean != null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_popup_share, null);
            final PopupWindow popupWindow = createPop(contentView);
            //分享给朋友
            LinearLayout llShareFriends = contentView.findViewById(R.id.ll_share_friends);
            llShareFriends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareToWechat(navigationBarDataBean, 0);
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
            });
            //分享朋友圈
            LinearLayout llShareMoments = contentView.findViewById(R.id.ll_share_moments);
            llShareMoments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareToWechat(navigationBarDataBean, 1);
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
            });
            popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED); //这句代码必须要才能获得正确的popupwindow的宽度
            int xOff;
            int buttonWidth = ivRightWeb.getWidth();
            int popupwindowWidth = popupWindow.getContentView().getMeasuredWidth();
            xOff = buttonWidth - popupwindowWidth + 45;
            popupWindow.showAsDropDown(ivRightWeb, xOff, 0);
        }
    }

    /**
     * 分享到微信
     *
     * @param navigationBarDataBean:
     * @param position               :
     */
    private void shareToWechat(final NavigationBarDataBean navigationBarDataBean, final int position) {
        if (navigationBarDataBean.getShareModel() != null) {
            Glide.with(BridgeWebViewActivity.this).asBitmap().
                    load(navigationBarDataBean.getShareModel().getImageUrl()).into(new SimpleTarget<Bitmap>() {
                /**
                 * 成功的回调
                 */
                @Override
                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    // 下面这句代码是一个过度dialog，因为是获取网络图片，需要等待时间
                    if (position == 0) {
                        ShareUtils.shareWeb(BridgeWebViewActivity.this, navigationBarDataBean.getShareModel().getShareTitle(),
                                navigationBarDataBean.getShareModel().getShareDescription(), bitmap,
                                navigationBarDataBean.getShareModel().getShareUrl(), "1");
                    } else if (position == 1) {
                        ShareUtils.shareWeb(BridgeWebViewActivity.this, navigationBarDataBean.getShareModel().getShareTitle(),
                                navigationBarDataBean.getShareModel().getShareDescription(), bitmap,
                                navigationBarDataBean.getShareModel().getShareUrl(), "2");
                    }
                }

                /**
                 * 失败的回调
                 */
                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    if (position == 0) {
                        ShareUtils.shareWeb(BridgeWebViewActivity.this, navigationBarDataBean.getShareModel().getShareTitle(),
                                navigationBarDataBean.getShareModel().getShareDescription(), null,
                                navigationBarDataBean.getShareModel().getShareUrl(), "1");
                    } else if (position == 1) {
                        ShareUtils.shareWeb(BridgeWebViewActivity.this, navigationBarDataBean.getShareModel().getShareTitle(),
                                navigationBarDataBean.getShareModel().getShareDescription(), null,
                                navigationBarDataBean.getShareModel().getShareUrl(), "2");
                    }
                }
            });
        }
    }

    /**
     * 创建PopWindow窗体
     *
     * @param contentView
     * @return
     */
    private static PopupWindow createPop(View contentView) {
        PopupWindow mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        //设置属性，使popwindow不受dialog的影响。
        mPopupWindow.setClippingEnabled(false);
        return mPopupWindow;
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

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        super.onMessageEvent(event);
        if (event != null) {
            if (bridgeWebview != null) {
                bridgeWebview.callHandler("GDINativePushData", event.getData(),
                        getResponseCallback("bridgeWebView"));
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMyBroadcastReceiver);
        super.onDestroy();
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //接收页面传过来的原生栏的配置
            navigationBarDataBean = (NavigationBarDataBean) intent.getSerializableExtra("navigationBarData");
            setNavigationBarData(navigationBarDataBean);
            Log.i("BroadcastReceiver", "event::: " + navigationBarDataBean);
        }
    }
}