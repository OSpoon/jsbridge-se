package com.spoon.app.jsbridge_n22.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.spoon.app.jsbridge_n22.R;

import pl.droidsonroids.gif.GifDrawable;


/**
 * 两个按钮的弹框
 *
 * @author gdk 2019/08/01
 */
public class LoadingDialog extends Dialog {

    private ImageView ivLoading;
    private GifDrawable gifDrawable;

    public LoadingDialog(Context context) {
        super(context, R.style.PubDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        initView();
        //单击dialog之外的地方，不可以dismiss掉dialog
        setCanceledOnTouchOutside(false);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        showImage();
    }

    private void initView() {
        ivLoading = findViewById(R.id.iv_loading);
    }

    /**
     * 显示进度条
     */
    public void showImage() {
        gifDrawable = (GifDrawable) ivLoading.getDrawable();
        if (gifDrawable != null) {
            gifDrawable.start(); //开始播放
        }
    }
}
