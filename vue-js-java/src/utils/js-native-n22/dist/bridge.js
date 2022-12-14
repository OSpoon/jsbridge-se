'use strict'

Object.defineProperty(exports, '__esModule', {
  value: true
})
var u = navigator.userAgent

function setupWebViewJavascriptBridge(callback) {
  if (!/(iPhone|iPad|iPod|iOS)/i.test(u)) {
    if (window.WebViewJavascriptBridge) {
      console.info('WebViewJavascriptBridgeReady is Ready')
      callback(window.WebViewJavascriptBridge)
    } else {
      console.info('WebViewJavascriptBridgeReady Waiting to be ready')
      document.addEventListener('WebViewJavascriptBridgeReady', function() {
        console.info('listener WebViewJavascriptBridgeReady is finish')
        // 默认注册一个供Java验证连接成功函数
        // window.WebViewJavascriptBridge.init(function(message, responseCallback) {
        //   console.info(message)
        //   responseCallback('JS PONG')
        // })
        callback(window.WebViewJavascriptBridge)
      }, false)
    }
  }
}

exports.default = {
  /**
   * 调用一个Java端已提供的方法
   * @param {Java暴露的接口名称} name
   * @param {参入到Java的数据,请传JSON对象} data
   * @param {接收Java回传的参数} callback
   */
  callhandler: function callhandler(name, data, _callback) {
    if (!/(iPhone|iPad|iPod|iOS)/i.test(u)) {
      console.log('bridge callhandler android >>> ', name, data)
      setupWebViewJavascriptBridge(function(bridge) {
        bridge.callHandler(name, data, function(data) {
          data = JSON.parse(data)
          _callback(data)
        })
      })
    } else {
      console.log('bridge callhandler ios >>> ', name, data)
      window.GDIJSBridge.call({ method: name, data: data, callback: function callback(result) {
        result = JSON.parse(result)
        _callback(result)
      } })
    }
  },

  /**
   * 注册一个Js函数供Java端调用
   * @param {提供给Java端的js函数名称} name
   * @param {接接收Java回调的数据并再次回调回Java端} callback
   */
  registerhandler: function registerhandler(name, _callback2) {
    if (!/(iPhone|iPad|iPod|iOS)/i.test(u)) {
      console.log('bridge registerhandler android >>> ', name)
      setupWebViewJavascriptBridge(function(bridge) {
        bridge.registerHandler(name, function(data, responseCallback) {
          _callback2(data, responseCallback)
        })
      })
    } else {
      console.log('bridge registerhandler ios >>> ', name)
      window.GDIJSBridge.call({ method: 'GDINativePushData', data: { name: name }, callback: function callback(result) {
        _callback2(result)
      } })
    }
  }
}
