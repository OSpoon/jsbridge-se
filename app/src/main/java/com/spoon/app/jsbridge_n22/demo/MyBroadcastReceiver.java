package com.spoon.app.jsbridge_n22.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * author : zhangxin
 * date : 2020-03-27 14:30
 * description : 用于接收js通过插件push到原生中的数据
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String event = intent.getStringExtra("event");
        String agentName = intent.getStringExtra("agentName");
        String agentCode = intent.getStringExtra("agentCode");
        String orgCode = intent.getStringExtra("orgCode");
        Log.i("BroadcastReceiver", "event::: " + event + "  agentName::: " + agentName + " agentCode::: " + agentCode + " orgCode::: " + orgCode);
    }
}
