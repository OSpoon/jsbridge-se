package com.ospoon.app.sunlife.jsbridge_plugins_n22.plugins;

import android.content.Intent;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.request.LocationJsRequest;
import com.ospoon.app.sunlife.jsbridge_plugins_n22.response.LocationJsResponse;
import com.spoon.app.jsbridge_n22.base.BaseBridgeHandler;
import com.spoon.app.jsbridge_n22.core.BridgePlugin;
import com.spoon.app.jsbridge_n22.utils.ResultUtil;
import com.yanzhenjie.permission.runtime.Permission;

/**
 * author : zhangxin
 * date : 2020-03-20 14:48
 * description : 高德定位插件
 */
@BridgePlugin(name = "location")
public class LocationBridgeHandler extends BaseBridgeHandler implements AMapLocationListener {

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    public String[] authorization() {
        return new String[]{Permission.ACCESS_COARSE_LOCATION,
                Permission.ACCESS_FINE_LOCATION,
                Permission.WRITE_EXTERNAL_STORAGE,
                Permission.READ_EXTERNAL_STORAGE,
                Permission.READ_PHONE_STATE};
    }

    @Override
    public Boolean registerMaInterface() {
        return false;
    }

    @Override
    public void process(String data) {
        try{
            LocationJsRequest locationJsRequest = new Gson().fromJson(data, LocationJsRequest.class);
            getLocationClient(locationJsRequest).startLocation();
        }catch (Exception e){
            callBack.onCallBack(ResultUtil.error("1", "The format of the request parameter is wrong, please check~"));
        }
    }

    /**
     * 获取AMap客户段
     *
     * @return
     */
    private AMapLocationClient getLocationClient(LocationJsRequest request) {
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(request.isNeedAddress());
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(request.isMockEnable());
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(Long.valueOf(request.getHttpTimeOut()));
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        return mLocationClient;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        //声明定位回调监听器
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                LocationJsResponse response = new LocationJsResponse();
                response.setLatitude(amapLocation.getLatitude());//获取纬度
                response.setLongitude(amapLocation.getLongitude());//获取经度
                response.setAccuracy(amapLocation.getAccuracy());//获取精度信息
                response.setAddress(amapLocation.getAddress());//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                response.setCountry(amapLocation.getCountry());//国家信息
                response.setProvince(amapLocation.getProvince());//省信息
                response.setCity(amapLocation.getCity());//城市信息
                response.setDistrict(amapLocation.getDistrict());//城区信息
                response.setStreet(amapLocation.getStreet());//街道信息
                response.setStreetNum(amapLocation.getStreetNum());//街道门牌号信息
                response.setCityCode(amapLocation.getCityCode());//城市编码
                response.setAdCode(amapLocation.getAdCode());//地区编码
                response.setAoiName(amapLocation.getAoiName());//获取当前定位点的AOI信息
                response.setBuildingId(amapLocation.getBuildingId());//获取当前室内定位的建筑物Id
                response.setFloor(amapLocation.getFloor());//获取当前室内定位的楼层
                response.setGpsAccuracyStatus(amapLocation.getGpsAccuracyStatus());//获取GPS的当前状态
                response.setTime(amapLocation.getTime());//获取定位时间
                //可在其中解析amapLocation获取相应内容。
                callBack.onCallBack(ResultUtil.success(response));
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                callBack.onCallBack(ResultUtil.error(String.valueOf(amapLocation.getErrorCode()), amapLocation.getErrorInfo()));
            }
        }
    }
}
