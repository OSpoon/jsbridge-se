package com.spoon.app.jsbridge_n22.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.adapter.NavigationBarAdapter;
import com.spoon.app.jsbridge_n22.bean.NavigationBarDataBean;

import java.util.Collections;
import java.util.List;


/**
 * 自定义titleBar
 *
 * @author gdk
 */
public class BaseTitleBar {

    /**
     * 左侧/右侧图标和中间标题
     */
    private View titleView;

    /**
     * 左侧/右侧图标和中间标题
     */
    private RelativeLayout rl_title_bar;
    /**
     * 左侧图标
     */
    private ImageView iv_left_icon;

    /**
     * 右侧图标
     */
    private ImageView iv_rightIco;

    /**
     * 中间标题
     */
    private TextView tv_title_middle;

    /**
     * 左侧关闭按钮标题
     */
    private TextView tvClose;
    /**
     * 左侧关闭按钮标题
     */
    private RecyclerView recyclerViewFunction;
    private Activity activity;
    private NavigationBarAdapter navigationBarAdapter;
    private List<NavigationBarDataBean.NavigationBarBean.ChangeRightImageBean> changeRightImageBeanList;

    /**
     * 构造方法：用于获取对象
     */
    public BaseTitleBar(Activity context) {
        this.activity = context;
        titleView = context.findViewById(R.id.rl_title_bar);
        rl_title_bar = titleView.findViewById(R.id.rl_title_bar);
        tv_title_middle = titleView.findViewById(R.id.tv_title_middle);
        iv_left_icon = titleView.findViewById(R.id.iv_back);
        tvClose = titleView.findViewById(R.id.tv_close);
        iv_rightIco = titleView.findViewById(R.id.iv_right);
        recyclerViewFunction = titleView.findViewById(R.id.recyclerView_function);

        //图标列表
        GridLayoutManager layoutManagerFunction = new GridLayoutManager(activity, 4);
        navigationBarAdapter = new NavigationBarAdapter(R.layout.item_navigation_function, activity);
        recyclerViewFunction.setLayoutManager(layoutManagerFunction);
        recyclerViewFunction.setAdapter(navigationBarAdapter);

    }

    public BaseTitleBar(NavigationBarDataBean navigationBarDataBean) {
        if (navigationBarDataBean != null) {
            changeRightImageBeanList = navigationBarDataBean.getNavigationBar().getChangeRightImage();
            Collections.sort(changeRightImageBeanList);
            navigationBarAdapter.setNewData(changeRightImageBeanList);
        }
    }

    /**
     * 用于设置标题栏文字
     *
     * @param titleText 传入要设置的标题
     * @return
     */
    public BaseTitleBar setTitleText(String titleText) {
        if (!TextUtils.isEmpty(titleText)) {
            tv_title_middle.setText(titleText);
        }
        return this;
    }

    /**
     * 设置标题栏文字颜色
     *
     * @return
     */
    public BaseTitleBar setTitleTextColor() {
        tv_title_middle.setTextColor(Color.WHITE);
        return this;
    }

    /**
     * 设置标题栏右边的文字
     *
     * @return
     */
    public BaseTitleBar setTitleRight(String rightTitle) {
        if (!TextUtils.isEmpty(rightTitle)) {
            iv_rightIco.setVisibility(View.GONE);
        }
        return this;
    }


    /**
     * 用于设置标题栏左边要显示的图片
     *
     * @param resId 标题栏左边的图标的id，一般为返回图标
     * @return
     */
    public BaseTitleBar setLeftIco(int resId) {
        iv_left_icon.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        iv_left_icon.setImageResource(resId);
        return this;
    }


    /**
     * 用于设置标题栏右边要显示的图片
     *
     * @param resId 标题栏右边的图标id
     * @return
     */
    public BaseTitleBar setRightIco(int resId) {
        iv_rightIco.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        iv_rightIco.setImageResource(resId);
        return this;
    }

    /**
     * 用户设置 标题栏右侧的图标的背景drawable
     *
     * @param resId drawable的id
     * @return
     */
    public BaseTitleBar setRightIconBgDr(int resId) {
        iv_rightIco.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        // iv_rightIco.setImageResource(R.drawable.ic_back_selector);
        return this;
    }

    /**
     * 用于设置标题栏左边图片的单击事件
     *
     * @param listener 传入的事件对象
     * @return
     */
    public BaseTitleBar setLeftIcoListening(View.OnClickListener listener) {
        if (iv_left_icon.getVisibility() == View.VISIBLE) {
            iv_left_icon.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 用于设置标题栏右边图片的单击事件
     *
     * @param listener 传入的事件对象
     * @return
     */
    public BaseTitleBar setRightListening(View.OnClickListener listener) {
        if (iv_rightIco.getVisibility() == View.VISIBLE) {
            iv_rightIco.setOnClickListener(listener);
        }
        return this;
    }

}