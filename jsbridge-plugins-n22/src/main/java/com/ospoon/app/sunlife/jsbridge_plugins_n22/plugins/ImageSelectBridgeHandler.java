package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.app.Activity;
import android.content.Intent;

import com.ospoon.app.sunlife.jsbridge_plugins_n22.Constants;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : zhangxin
 * date : 2020-03-25 16:02
 * description :
 */
@Deprecated
@BridgePlugin(name = "imageSelect")
public class ImageSelectBridgeHandler extends BaseBridgeHandler {
    @Override
    public String[] authorization() {
        return new String[0];
    }

    @Override
    public Boolean registerMaInterface() {
        return true;
    }

    @Override
    public void process(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            int limit = jsonObject.getInt("limit");
            Intent intent = new Intent(getActivity(), PhotoSelectorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("limit", limit);//number是选择图片的数量
            getActivity().startActivityForResult(intent, Constants.OPEN_IMAGE_SELECT_REQUEST_CODE);
        } catch (JSONException e) {
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.OPEN_IMAGE_SELECT_REQUEST_CODE) {
            if (null != data) {
                if (resultCode == Activity.RESULT_OK) {
                    List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    Map<String,List<String>> map = new HashMap<>();
                    map.put("paths",paths);
                    callBack.onCallBack(ResultUtil.success(map));
                }
            }
        }
    }
}
