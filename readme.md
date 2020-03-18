### jsbridge-n22使用指南 ###

#### 1. 在模块级别的`build.gradle`添加jsbridge-n22依赖
```
implementation 'com.ospoon:jsbridge-n22:1.0.4'
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
 
#### 4. JS调用Java ####
```java
window.WebViewJavascriptBridge.callHandler(
    'toast'                     //桥注册的名称ID
    , {'msg': '中文测试'}        //传递给原生的参数
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
    在定义的插件中可以取到getActivity(),尝试使用getActivity()做后续操作
    ```
4. 申请权限
    ```
    申请权限已经在BaseBridgeHandler操作,只需要将申请的权限通过authorization()返回即可,注意权限使用了
    `com.yanzhenjie.permission:support:2.0.0`,所以权限常量请在`com.yanzhenjie.permission.runtime.Permission`
    中查看
    ```

#### 6. 扩展js部分(`npm i js-native-n22`安装后即可使用) ####
1. 新建`bridge.js`
    ```js
    // 判断机型
    const u = navigator.userAgent
    
    function setupWebViewJavascriptBridge(callback) {
      if (!/(iPhone|iPad|iPod|iOS)/i.test(u)) {
        if (window.WebViewJavascriptBridge) {
          console.info('WebViewJavascriptBridgeReady is finish')
          callback(window.WebViewJavascriptBridge)
        } else {
          console.info('listener WebViewJavascriptBridgeReady')
          document.addEventListener(
            'WebViewJavascriptBridgeReady',
            function() {
              console.info('listener WebViewJavascriptBridgeReady is finish')
              // 默认注册一个供Java验证连接成功函数
              window.WebViewJavascriptBridge.init(function(message, responseCallback) {
                console.info(message)
                responseCallback('JS PONG')
              })
              callback(window.WebViewJavascriptBridge)
            },
            false
          )
        }
      }
    }
    
    export default {
      callhandler(name, data, callback) {
        console.log('bridge callhandler >>> ', name, data)
        setupWebViewJavascriptBridge(function(bridge) {
          bridge.callHandler(name, data, function(data) {
            data = JSON.parse(data)
            callback(data)
          })
        })
      },
      registerhandler(name, callback) {
        console.log('bridge registerhandler >>> ', name)
        setupWebViewJavascriptBridge(function(bridge) {
          bridge.registerHandler(name, function(data, responseCallback) {
            callback(data, responseCallback)
          })
        })
      }
    }
    
    ```
2. 新建`native.js`
    ```js
    import bridge from './bridge'
    
    const native = {
      toast(message, success, fail) {
        bridge.callhandler('toast', { 'message': message }, (result) => {
          if (!result.error) {
            success(result.content)
          } else {
            fail(result.content)
          }
        })
      },
      openOther(message, success, fail) {
        bridge.callhandler('openOther', { 'message': message }, (result) => {
          if (!result.error) {
            success(result.content)
          } else {
            fail(result.content)
          }
        })
      },
      qrCodeScan(message, success, fail) {
        bridge.callhandler('qrscan', { 'message': message }, (result) => {
          if (!result.error) {
            success(result.content)
          } else {
            fail(result.content)
          }
        })
      },
      getLocationInfo(message, success, fail) {
        bridge.callhandler('location', { 'message': message }, (result) => {
          if (!result.error) {
            success(result.content)
          } else {
            fail(result.content)
          }
        })
      },
      getDevice(message, success, fail) {
        bridge.callhandler('device', { 'message': message }, (result) => {
          if (!result.error) {
            success(result.content)
          } else {
            fail(result.content)
          }
        })
      },
      closePage(message, success, fail) {
        bridge.callhandler('close', { 'message': message }, (result) => {
          if (!result.error) {
            success(result.content)
          } else {
            fail(result.content)
          }
        })
      }
    }
    
    export default native
    
    ```

 注意事项:
 1. 页面提示ERR_CACHE_MISS:请设置网络权限`<uses-permission android:name="android.permission.INTERNET"/>`
 2. 页面提示ERR_CLEARTEXT_NOT_PERMITTED:请在`AndroidManifest.xml`的`application`节点增加`android:usesCleartextTraffic="true"`
 

使用方案为[JSBridge-Android](https://github.com/smallbuer/JSBridge-Android)

发布地址[bintray](https://bintray.com/spoon2014)