package com.spoon.app.jsbridge_n22.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spoon.app.jsbridge_n22.R;
import com.spoon.app.jsbridge_n22.bean.NavigationBarDataBean;

/**
 * Created by gdk on 2020/5/26 16:04
 *
 * @author thinkpad
 * 自定义导航栏的适配器
 */
public class NavigationBarAdapter
        extends BaseQuickAdapter<NavigationBarDataBean.NavigationBarBean.ChangeRightImageBean
        , BaseViewHolder> {
    private Activity mActivity;

    public NavigationBarAdapter(int layoutId, Activity activity) {
        super(layoutId);
        this.mActivity = activity;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(BaseViewHolder
                                   holder, NavigationBarDataBean.NavigationBarBean.ChangeRightImageBean bean) {
        ImageView ivPolicyImg = holder.getView(R.id.iv_function_claims);

        switch (bean.getImage()) {
            case "0":
                Glide.with(mActivity).load(R.drawable.img_common_add).into(ivPolicyImg);
                break;
            case "1":
                Glide.with(mActivity).load(R.drawable.img_common_share).into(ivPolicyImg);
                break;
            case "2":
                Glide.with(mActivity).load(R.drawable.img_common_search).into(ivPolicyImg);
                break;
            default:
                break;
        }
    }
}
