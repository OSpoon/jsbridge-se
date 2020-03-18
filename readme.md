## jsbridge-n22 ##

使用方案为[JSBridge-Android](https://github.com/smallbuer/JSBridge-Android)

发布地址[bintray](https://bintray.com/spoon2014)

```
implementation 'com.ospoon:jsbridge-n22:1.0.1'
```

#### 1. 新建插件Java类(如:ToastBridgeHandler),并继承自BaseBridgeHandler ####

    public class ToastBridgeHandler extends BaseBridgeHandler {
    
        /**
         * 权限数组,不申请权限设置为null
         * @return
         */
        @Override
        public String[] authorization() {
            return null;
        }
    
        /**
         * 是否支持通过onActivityResult回调数据
         * @return
         */
        @Override
        public Boolean registerMaInterface() {
            return null;
        }
    
        /**
         * 业务流程
         * @param data
         */
        @Override
        public void process(String data) {
           Toast.makeText(mContext,"js data:" + data, Toast.LENGTH_SHORT).show();
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
    
#### 2. 新建AppContext类并继承Application ####

#### 3. 在AppContext中注册插件 ####

    public class AppContext extends Application {
    
        public final static String HANDLER_NAME_TOAST = "toast";
    
        @Override
        public void onCreate() {
            super.onCreate();
            Map<String, BridgeHandler> handlerMap = new HashMap<>();
            handlerMap.put(HANDLER_NAME_TOAST,new ToastBridgeHandler());
            Bridge.INSTANCE.registerHandler(handlerMap);
        }
    }
    
#### 4. 一键启动Webview ####

    public class MainActivity extends AppCompatActivity {
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            BridgeWebViewActivity.start(MainActivity.this, AppContext.ROOT_URL);
        }
    }
    
#### 5. js调用java插件 ####

    window.WebViewJavascriptBridge.callHandler(
        'toast'                     //桥注册的名称ID
        , {'msg': '中文测试'}        //传递给原生的参数
        , function(responseData) {  //异步回调接口
            console.log('native return->'+responseData);
        }
    );

#### 扩展一 ####
##### 6-1. H5中做基础配置(导入bridge.js) #####
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
##### 6-2. H5中做基础配置(导入native.js) #####
    import bridge from './bridge'
    const native = {
      toast(success, fail) {
        bridge.callhandler('toast', { 'msg': '中文测试' }, (result) => {
          if (!result.error) {
            success(result.content)
          } else {
            fail(result.content)
          }
        })
      }
    }
    
    export default native
    
##### 6-3. 调用插件 ##### 

    native.toast((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
      
#### 扩展二 ####
##### 7-1. 使用固定方式将数据返回到H5 #####
    在定义的插件中可以取到mCallBackFunction对象,用于将数据回调到H5
    使用方式:
    1. 成功情况:
        ```java
        mCallBackFunction.onCallBack(ResultUtil.success(JSONObject));
        ```
    2. 失败情况:
        ```java
        mCallBackFunction.onCallBack(ResultUtil.error("1","取消识别"));
        mCallBackFunction.onCallBack(ResultUtil.error("1",e.getMessage()));
        ```
##### 7-2. 使用上下文 #####
    在定义的插件中可以取到mContext, new Intent(mContext, CaptureActivity.class);
    
##### 7-3. 启动一个带回调的Activity #####
    在定义的插件中可以取到mContext,尝试强转为(com.spoon.app.jsbridge_n22.base.BaseActivity)
    
##### 7-4. 申请权限 #####
    申请权限已经在BaseBridgeHandler操作,只需要将申请的权限通过authorization()返回即可,注意权限使用了
    `com.yanzhenjie.permission:support:2.0.0`,所以权限常量请在`com.yanzhenjie.permission.runtime.Permission`
    中查看