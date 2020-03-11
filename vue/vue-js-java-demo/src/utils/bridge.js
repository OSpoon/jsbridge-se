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
