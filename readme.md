### jsbridge-n22使用指南 ###

[演示APK下载地址](http://android.n22.online/bdph)

#### 1. 在模块级别的`build.gradle`添加jsbridge-n22依赖
> 如无法更新全依赖包,请配置maven地址:https://dl.bintray.com/spoon2014/maven

```
//仅包含WebView中Js与Java交互,插件需按指定格式编写
implementation 'com.ospoon:jsbridge-n22:1.0.9'

//包含WebView中Js与Java交互和基础插件,节省开发
implementation 'com.ospoon:jsbridge-plugins-n22:1.0.9'
```

#### 2. 创建插件 #####

1. 新建插件Java类(如:ToastBridgeHandler),并继承自BaseBridgeHandler
2. 在新建的插件Java类上使用注解`@BridgePlugin(name="xxx")`标注插件名称
3. 插件各部分简介
    ```java
    /*插件名称,js调用时会使用到*/
    @BridgePlugin(name="toast")
    public class ToastBridgeHandler extends BaseBridgeHandler {
    
        /**
         * 需申请权限列表
         * 权限常量请在`com.yanzhenjie.permission.runtime.Permission`
         * 中查看
         * @return
         */
        @Override
        public String[] authorization() {
            return new String[0];
        }
    
        /**
         * 是否开启`onActivityResult`回调数据
         * @return
         */
        @Override
        public Boolean registerMaInterface() {
            return false;
        }
    
        /**
         * 执行业务处理
         * @param data
         */
        @Override
        public void process(String data) {
    
        }
    
        /**
         * 接收回调数据
         * @param requestCode
         * @param resultCode
         * @param intent
         */
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    
        }
    }
    ```
4. 在`Application`的子类`onCreate`方法中注册插件
    ```java
    /*
        参数支持同时传入多个
    */
    Bridge.INSTANCE.registerHandler(ToastBridgeHandler.class);
    ```
 #### 3. 一键启动
 1. BridgeWebViewActivity
     ```java
     BridgeWebViewActivity.start(this,"http://192.168.199.163:9999");
     ```
 2. X5WebViewActivity
    ```java
    X5WebViewActivity.start(this,"http://192.168.199.163:9999");
    ```
 
#### 4. JS调用Java([js-native-n22](./vue-js-java/src/utils/js-native-n22/readme.md)对调用进行了封装) ####
```java
window.WebViewJavascriptBridge.callHandler(
    'toast'                     //桥注册的名称ID
    , { text: '你好啊赛利亚', duration: 0 }        //传递给原生的参数
    , function(responseData) {  //异步回调接口
        console.log('native return->'+responseData);
    }
);
```

#### 5. 其他 ####
1. 回调数据到Js
> 在定义的插件中可以取到callBack对象,用于将数据回调到H5
使用方式:
1. 成功情况:
    ```java
    callBack.onCallBack(ResultUtil.success(JSONObject));
    ```
2. 失败情况:
    ```java
    callBack.onCallBack(ResultUtil.error("1","取消识别"));
    callBack.onCallBack(ResultUtil.error("1",e.getMessage()));
    ```
2. 使用上下文
    ```
    在定义的插件中可以取到getActivity(), new Intent(getActivity(), CaptureActivity.class);
    ```
3. 启动一个带回调的Activity
    ```
    在定义的插件中可以取到getActivity(),尝试使用getActivity().startActivityForResult()操作
    ```
4. 申请权限
    ```
    申请权限已经在BaseBridgeHandler操作,只需要将申请的权限通过authorization()返回即可,注意权限使用了
    `com.yanzhenjie.permission:support:2.0.0`,所以权限常量请在`com.yanzhenjie.permission.runtime.Permission`
    中查看
    ```

#### 6. 特殊配置 ####
1. 如需使用高德定位插件需配置高德定位apikey到app的AndroidManifest.xml
    ```
    <meta-data android:name="com.amap.api.v2.apikey" android:value="xxx">
    </meta-data>
    ```
2. 部分插件需使用到文件存储,需配置一下信息
    `app/src/main/res/xml/provider_paths.xml`
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <paths xmlns:android="http://schemas.android.com/apk/res/android">
        <!-- /storage/emulated/0/Download/com.bugly.upgrade.demo/.beta/apk-->
        <external-path name="beta_external_path" path="Download/"/>
        <!--/storage/emulated/0/Android/data/com.bugly.upgrade.demo/files/apk/-->
        <external-path name="beta_external_files_path" path="Android/data/"/>
        <!--对应外部内存卡根目录：Environment.getExternalStorageDirectory()-->
        <external-path name="external-path" path="." />
    </paths>
    ```
    
    `app的AndroidManifest.xml`
    ```xml
    <provider
        android:name="android.support.v4.content.FileProvider"
        android:authorities="${applicationId}.fileProvider"
        android:exported="false"
        android:grantUriPermissions="true">
        <meta-data
            android:name="android.support.FILE_PROVIDER_PATHS"
            android:resource="@xml/provider_paths"/>
    </provider>
    ```
3. 如需使用语音听写插件,需在Application中进行初始化Appid操作
    ```java
    //注册科大讯飞语音听写
    SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID +"=xxxxx");
    ```
    如需进行移动统计需在app模块的`AndroidManifest.xml`中进行添加
    ```xml
    <!-- 科大讯飞移动统计分析 -->
    <meta-data
        android:name="IFLYTEK_APPKEY"
        android:value="'xxxxx'" />
    <meta-data
        android:name="IFLYTEK_CHANNEL"
        android:value="xxxxx" />
    ```

4. 如需使用`pushData`插件接收H5端的数据,插件中使用的广播形式,集成模块后定义广播接收器并动态注册接收数据
    ```java
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
    ```
    
    ```java
    public class MainActivity extends Activity {
    
        MyBroadcastReceiver mMyBroadcastReceiver;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //注册广播用于接收Js通过插件Push到原生的数据
            mMyBroadcastReceiver = new MyBroadcastReceiver();
            registerReceiver(mMyBroadcastReceiver, new IntentFilter(Constants.JSBRIDGEN22_JS_PUSH_DATA_ACTION));
        }
    
        @Override
        protected void onDestroy() {
            unregisterReceiver(mMyBroadcastReceiver);
            super.onDestroy();
        }
    }
    ```



 注意事项:
 1. 页面提示ERR_CACHE_MISS:请设置网络权限`<uses-permission android:name="android.permission.INTERNET"/>`
 2. 页面提示ERR_CLEARTEXT_NOT_PERMITTED:请在`AndroidManifest.xml`的`application`节点增加`android:usesCleartextTraffic="true"`
 3. 如发生样式冲突请在app模块中的添加AndroidManifest.xml文件的application节点添加`tools:replace="android:theme"`
 4. 提交代码提示`validate-commit-msg: command not found`,请全局安装插件,执行命令:`cnpm install validate-commit-msg -g`,因内部包含vue项目中配置提交钩子

使用方案为[JSBridge-Android](https://github.com/smallbuer/JSBridge-Android)

发布地址[bintray](https://bintray.com/spoon2014)

