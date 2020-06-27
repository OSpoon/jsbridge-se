package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.response.OpenContactsResponse;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取联系人列表信息的桥接方法
 *
 * @author gdk
 */
@BridgePlugin(name = "openContacts")
public class OpenContactsBridgeHandler extends BaseBridgeHandler {

    /**
     * 权限数组,不申请权限设置为null
     *
     * @return
     */
    @Override
    public String[] authorization() {
        return new String[]{Permission.READ_CONTACTS};
    }

    /**
     * 是否支持通过onActivityResult回调数据
     *
     * @return
     */
    @Override
    public Boolean registerMaInterface() {
        return false;
    }

    /**
     * 业务流程
     *
     * @param data
     */
    @Override
    public void process(String data) {
        getContacts();
    }

    /**
     * 获取联系人列表
     */
    private void getContacts() {
        List<OpenContactsResponse> list = new ArrayList<>();
        OpenContactsResponse openContactsResponse;
        Cursor cursor = null;
        try {
            cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                int count = 0;
                while (cursor.moveToNext()) {
                    count++;
                    openContactsResponse = new OpenContactsResponse();
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //设置名字
                    openContactsResponse.setName(displayName);
                    //设置手机号码
                    openContactsResponse.setMobilePhone(number);
                    //设置ID
                    openContactsResponse.setId(String.valueOf(count));
                    list.add(openContactsResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        Log.e("tag", "getContacts: " + list.toString());
        //将结果返回给页面
        if (list.size() > 0) {
            Map<String, List<OpenContactsResponse>> map = new HashMap<>();
            map.put("data", list);
            callBack.onCallBack(ResultUtil.success(map));
        } else {
            callBack.onCallBack(ResultUtil.error("1", "通讯录无联系人存在，请先新建联系人！"));
        }

    }

    /**
     * 接收回调数据
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
