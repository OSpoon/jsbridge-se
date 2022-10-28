package com.spoon.app.jsbridge_se.core;


import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 */
public class BridgeUtil {


    private String TAG = "BridgeUtil";

    public static final int URL_MAX_CHARACTER_NUM = 2097152;
    public static final String JAVA_SCRIPT = "WebViewJavascriptBridge.js";
    public final static String CALLBACK_ID_FORMAT = "JAVA_CB_%s";
    public final static String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');";
    public final static String JAVASCRIPT_STR = "javascript:%s";
    public final static String UNDERLINE_STR = "_";
    public final static String WebviewJavascriptBridge =
            "(function(){if(window.WebViewJavascriptBridge){return}var receiveMessageQueue=[];var messageHandlers={};var responseCallbacks={};var uniqueId=1;function init(messageHandler){if(WebViewJavascriptBridge._messageHandler){throw new Error('WebViewJavascriptBridge.init called twice');}WebViewJavascriptBridge._messageHandler=messageHandler;var receivedMessages=receiveMessageQueue;receiveMessageQueue=null;for(var i=0;i<receivedMessages.length;i++){_dispatchMessageFromNative(receivedMessages[i])}}function send(data,responseCallback){_doSend('jsbridge','send',data,responseCallback)}function registerHandler(handlerName,handler){messageHandlers[handlerName]=handler}function callHandler(handlerName,data,responseCallback){_doSend('jsbridge',handlerName,data,responseCallback)}function callHandlerWithModule(moduleName,handlerName,data,responseCallback){_doSend(moduleName,handlerName,data,responseCallback)}function _doSend(moduleName,handlerName,message,responseCallback){var callbackId;if(typeof responseCallback==='string'){callbackId=responseCallback}else if(responseCallback){callbackId='cb_'+(uniqueId++)+'_'+new Date().getTime();responseCallbacks[callbackId]=responseCallback}else{callbackId=''}try{var evalStr1='window.'+moduleName+'.';if(moduleName=='jsbridge'&&handlerName!='response'){evalStr1+='handler'}else{evalStr1+=handlerName}var fn=eval(evalStr1)}catch(e){console.log(e)}if(typeof fn==='function'){var evalStr='window.'+moduleName;var fnwindow=eval(evalStr);var responseData;if(moduleName=='jsbridge'&&handlerName!='response'){responseData=fn.call(fnwindow,handlerName,JSON.stringify(message),callbackId)}else{responseData=fn.call(fnwindow,JSON.stringify(message),callbackId)}if(responseData){responseCallback=responseCallbacks[callbackId];if(!responseCallback){return}responseCallback(responseData);delete responseCallbacks[callbackId]}}}function _dispatchMessageFromNative(messageJSON){setTimeout(function(){var message=JSON.parse(messageJSON);var responseCallback;if(message.responseId){responseCallback=responseCallbacks[message.responseId];if(!responseCallback){return}responseCallback(message.responseData);delete responseCallbacks[message.responseId]}else{if(message.callbackId){var callbackResponseId=message.callbackId;responseCallback=function(responseData){_doSend('jsbridge','response',responseData,callbackResponseId)}}var handler=WebViewJavascriptBridge._messageHandler;if(message.handlerName){handler=messageHandlers[message.handlerName]}try{handler(message.data,responseCallback)}catch(exception){if(typeof console!='undefined'){console.log(\"WebViewJavascriptBridge: WARNING: javascript handler threw.\",JSON.stringify(message),exception)}}}})}function _handleMessageFromNative(messageJSON){if(receiveMessageQueue){receiveMessageQueue.push(messageJSON)}_dispatchMessageFromNative(messageJSON)}var WebViewJavascriptBridge=window.WebViewJavascriptBridge={init:init,send:send,registerHandler:registerHandler,callHandler:callHandler,callHandlerWithModule:callHandlerWithModule,_handleMessageFromNative:_handleMessageFromNative};console.log(\"start jsbridge ...\");var doc=document;var readyEvent=doc.createEvent('Events');readyEvent.initEvent('WebViewJavascriptBridgeReady');readyEvent.bridge=WebViewJavascriptBridge;doc.dispatchEvent(readyEvent);console.log(\"end jsbridge ...\")})();";


    /**
     * 这里只是加载lib包中assets中的 WebViewJavascriptBridge.js
     * @param view webview
     * @param path 路径
     */
    public static String webViewLoadLocalJs(IWebView view, String path){
        return assetFile2Str(view.getContext(), path);
    }

    /**
     * 解析assets文件夹里面的代码,去除注释,取可执行的代码
     * @param c context
     * @param urlStr 路径
     * @return 可执行代码
     */
    public static String assetFile2Str(Context c, String urlStr){
        InputStream in = null;
        try{
            in = c.getAssets().open(urlStr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder sb = new StringBuilder();
            do {
                line = bufferedReader.readLine();
                if (line != null && !line.matches("^\\s*\\/\\/.*")) { // 去除注释
                    sb.append(line);
                }
            } while (line != null);

            bufferedReader.close();
            in.close();

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
}
