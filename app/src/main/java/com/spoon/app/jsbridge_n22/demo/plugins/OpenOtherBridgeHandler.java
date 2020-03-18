package com.spoon.app.jsbridge_n22.demo.plugins;

import android.content.Intent;

import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.demo.OtherActivity;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class OpenOtherBridgeHandler extends BaseBridgeHandler {

    private static final int REQUEST_CODE_OPEN_OTHER = 200;

    /**
     * 权限数组,不申请权限设置为null
     *
     * @return
     */
    @Override
    public String[] authorization() {
        return new String[]{Permission.READ_EXTERNAL_STORAGE};
    }

    /**
     * 是否支持通过onActivityResult回调数据
     *
     * @return
     */
    @Override
    public Boolean registerMaInterface() {
        return true;
    }

    /**
     * 业务流程
     *
     * @param data
     */
    @Override
    public void process(String data) {
        Intent intent = new Intent(getActivity(), OtherActivity.class);
        getActivity().startActivityForResult(intent, REQUEST_CODE_OPEN_OTHER);
    }

    /**
     * 接收回调数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_OPEN_OTHER && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra("msg");
                Map<String, String> map = new HashMap<>();
                map.put("msg", content);
                callBack.onCallBack(ResultUtil.success(map));
            }
        }
    }
}
