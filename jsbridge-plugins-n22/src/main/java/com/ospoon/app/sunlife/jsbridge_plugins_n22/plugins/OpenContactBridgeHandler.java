package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.response.OpenContactsResponse;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取联系人信息的桥接方法
 *
 * @author gdk
 */
@BridgePlugin(name = "openContact")
public class OpenContactBridgeHandler extends BaseBridgeHandler {

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
        return true;
    }

    /**
     * 业务流程
     *
     * @param data
     */
    @Override
    public void process(String data) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        getActivity().startActivityForResult(intent, 0);
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
        switch (requestCode) {
            case 0:
                if (intent == null) {
                    return;
                }
                Uri uri = intent.getData();
                OpenContactsResponse phoneContacts = getPhoneContacts(uri);
                if (phoneContacts != null) {
                    callBack.onCallBack(ResultUtil.success(phoneContacts));
                } else {
                    callBack.onCallBack(ResultUtil.error("1", "通讯录无联系人存在，请先新建联系人！"));
                }
            default:
                break;
        }
    }

    /**
     * 获取单个联系人
     *
     * @param uri
     * @return
     */
    private OpenContactsResponse getPhoneContacts(Uri uri) {
        OpenContactsResponse openContactsResponse = new OpenContactsResponse();
        //得到ContentResolver对象**
        ContentResolver cr = getActivity().getContentResolver();
        //取得电话本中开始一项的光标**
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //取得联系人姓名**
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            openContactsResponse.setName(cursor.getString(nameFieldColumnIndex));
            //取得电话号码**
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if (phone != null) {
                phone.moveToFirst();
                openContactsResponse.setMobilePhone(phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            }
            phone.close();
            cursor.close();
        } else {
            return null;
        }
        return openContactsResponse;
    }
}
