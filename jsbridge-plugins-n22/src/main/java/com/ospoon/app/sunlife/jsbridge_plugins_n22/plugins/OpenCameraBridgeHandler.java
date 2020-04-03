package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.ImgUtils.ImgUtils;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.OpenCameraJsRequest;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 打开相机和选择照片
 *
 * @author gdk
 */
@BridgePlugin(name = "openCamera")
public class OpenCameraBridgeHandler extends BaseBridgeHandler {
    private static final int REQUEST_CODE_OPEN_CAMERA = 100;
    private static final int REQUEST_CODE_SELECT_PICTURE = 101;
    private int compress;

    /**
     * 权限数组,不申请权限设置为null
     *
     * @return
     */
    @Override
    public String[] authorization() {
        return new String[]{Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE,
                Permission.WRITE_EXTERNAL_STORAGE};
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
        OpenCameraJsRequest request = new Gson().fromJson(data, OpenCameraJsRequest.class);
        compress = request.getIsCompress();
        if ("1".equals(request.getOpenFlag())) {
            PictureSelector.create(getActivity())
                    .openCamera(PictureMimeType.ofImage())
                    .forResult(REQUEST_CODE_OPEN_CAMERA);
        } else {
            PictureSelector.create(getActivity())
                    .openGallery(PictureMimeType.ofImage())
                    .maxSelectNum(1)
                    .imageSpanCount(4)
                    .isCamera(false)
                    .compress(true)
                    .forResult(REQUEST_CODE_SELECT_PICTURE);
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
        if (resultCode == Activity.RESULT_OK) {
            ArrayList<String> photoNamesList = new ArrayList<>();
            Map<String, List<String>> map = new HashMap<>();
            switch (requestCode) {
                //打开相机拍摄照片的回调
                case REQUEST_CODE_OPEN_CAMERA:
                    //获取照片的路径和名字
                    List<LocalMedia> localMediaTake = PictureSelector.obtainMultipleResult(intent);
                    for (LocalMedia media : localMediaTake) {
                        if (compress == 1) {
                            photoNamesList.addAll(ImgUtils.getCompressImg("", media.getPath()));
                        } else {
                            photoNamesList.add(media.getPath());
                        }

                    }
                    map.put("paths", photoNamesList);
                    callBack.onCallBack(ResultUtil.success(map));
                    break;
                //选择相册中的照片回调
                case REQUEST_CODE_SELECT_PICTURE:
                    List<LocalMedia> localMediaTake2 = PictureSelector.obtainMultipleResult(intent);
                    for (LocalMedia media : localMediaTake2) {
                        if (compress == 1) {
                            photoNamesList.addAll(ImgUtils.getCompressImg("", media.getPath()));
                        } else {
                            photoNamesList.add(media.getPath());
                        }
                    }
                    map.put("paths", photoNamesList);
                    callBack.onCallBack(ResultUtil.success(map));
                    break;
                default:
                    break;
            }
        }

    }
}
