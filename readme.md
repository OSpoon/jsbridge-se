### jsbridge-n22使用指南 ###

[演示APK下载地址](http://android.n22.online/bdph)

#### 1. 在模块级别的`build.gradle`添加jsbridge-n22依赖
```
//仅包含WebView中Js与Java交互,插件需按指定格式编写
implementation 'com.ospoon:jsbridge-n22:1.0.4'

//包含WebView中Js与Java交互和基础插件,节省开发
implementation 'com.ospoon:jsbridge-plugins-n22:1.0.4'
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

 注意事项:
 1. 页面提示ERR_CACHE_MISS:请设置网络权限`<uses-permission android:name="android.permission.INTERNET"/>`
 2. 页面提示ERR_CLEARTEXT_NOT_PERMITTED:请在`AndroidManifest.xml`的`application`节点增加`android:usesCleartextTraffic="true"`
 

使用方案为[JSBridge-Android](https://github.com/smallbuer/JSBridge-Android)

发布地址[bintray](https://bintray.com/spoon2014)

