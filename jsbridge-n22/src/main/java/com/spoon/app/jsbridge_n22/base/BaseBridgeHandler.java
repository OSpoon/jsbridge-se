package com.spoon.app.jsbridge_n22.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.spoon.app.jsbridge_n22.core.BridgeHandler;
import com.spoon.app.jsbridge_n22.core.CallBackFunction;
import com.spoon.app.jsbridge_n22.uiInterface.MAInterface;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

public abstract class BaseBridgeHandler extends BridgeHandler implements MAInterface {

    public CallBackFunction callBack;
    private BaseActivity activity;
    private FragmentActivity fragmentActivity;

    @SuppressLint("WrongConstant")
    @Override
    public void handler(Context context, final String jsData, CallBackFunction function) {
        if(context instanceof BaseActivity){
            this.activity = (BaseActivity)context;
        }
        this.callBack = function;
        if (registerMaInterface()) {
            this.activity.setMaInterface(this);
        }
        //检测是否授权
        String[] permissions = authorization();
        if (permissions != null && permissions.length > 0) {
            AndPermission.with(context).runtime()
                    .permission(permissions)
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            process(jsData);
                        }
                    }).onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {
                            Uri packageURI = Uri.parse("package:" + activity.getPackageName());
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                            Toast.makeText(activity, "请您正确授权后使用功能。", Toast.LENGTH_LONG).show();
                        }
                    }).start();
        } else {
            process(jsData);
        }
    }

    /**
     * 将所需要授权的权限列表按数组传入,否则传null
     *
     * @return
     */
    public abstract String[] authorization();

    /**
     * 传true后支持onActivityResult获取信息
     * @return
     */
    public abstract Boolean registerMaInterface();

    /**
     * 处理业务
     */
    public abstract void process(String data);

    /**
     * 获取WebView所在Activity实例
     * @return
     */
    public BaseActivity getActivity() {
        return this.activity;
    }
}
