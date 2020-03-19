package com.ospoon.app.sunlife.jsbridge_plugins_n22.response;

import java.io.Serializable;

/**
 * author : zhangxin
 * date : 2020-03-18 23:47
 * description :
 */
public class DeviceJsResponse implements Serializable {
    private boolean     isDeviceRooted;     //判断设备是否 rooted
    private boolean     isAdbEnabled;       //判断设备 ADB 是否可用
    private String      sDKVersionName;     //获取设备系统版本号
    private int         sDKVersionCode;     //获取设备系统版本码
    private String      androidID;          //获取设备 AndroidID
    private String      macAddress;         //获取设备 MAC 地址
    private String      manufacturer;       //获取设备厂商
    private String      model;              //获取设备型号
    private String[]    aBIs;               //获取设备 ABIs
    private boolean     isTablet;           //判断是否是平板
    private boolean     isEmulator;         //判断是否是模拟器
    private String      uniqueDeviceId;     //获取唯一设备 ID
    private boolean     isSameDevice;       //判断是否同一设备

    public boolean isDeviceRooted() {
        return isDeviceRooted;
    }

    public void setDeviceRooted(boolean deviceRooted) {
        isDeviceRooted = deviceRooted;
    }

    public boolean isAdbEnabled() {
        return isAdbEnabled;
    }

    public void setAdbEnabled(boolean adbEnabled) {
        isAdbEnabled = adbEnabled;
    }

    public String getsDKVersionName() {
        return sDKVersionName;
    }

    public void setsDKVersionName(String sDKVersionName) {
        this.sDKVersionName = sDKVersionName;
    }

    public int getsDKVersionCode() {
        return sDKVersionCode;
    }

    public void setsDKVersionCode(int sDKVersionCode) {
        this.sDKVersionCode = sDKVersionCode;
    }

    public String getAndroidID() {
        return androidID;
    }

    public void setAndroidID(String androidID) {
        this.androidID = androidID;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String[] getaBIs() {
        return aBIs;
    }

    public void setaBIs(String[] aBIs) {
        this.aBIs = aBIs;
    }

    public boolean isTablet() {
        return isTablet;
    }

    public void setTablet(boolean tablet) {
        isTablet = tablet;
    }

    public boolean isEmulator() {
        return isEmulator;
    }

    public void setEmulator(boolean emulator) {
        isEmulator = emulator;
    }

    public String getUniqueDeviceId() {
        return uniqueDeviceId;
    }

    public void setUniqueDeviceId(String uniqueDeviceId) {
        this.uniqueDeviceId = uniqueDeviceId;
    }

    public boolean isSameDevice() {
        return isSameDevice;
    }

    public void setSameDevice(boolean sameDevice) {
        isSameDevice = sameDevice;
    }
}
