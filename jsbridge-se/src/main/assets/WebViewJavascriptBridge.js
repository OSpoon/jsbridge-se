(function () {
    if (window.WebViewJavascriptBridge) {
        return
    }
    var receiveMessageQueue = [];
    var messageHandlers = {};
    var responseCallbacks = {};
    var uniqueId = 1;

    function init(messageHandler) {
        if (WebViewJavascriptBridge._messageHandler) {
            throw new Error('WebViewJavascriptBridge.init called twice');
        }
        WebViewJavascriptBridge._messageHandler = messageHandler;
        var receivedMessages = receiveMessageQueue;
        receiveMessageQueue = null;
        for (var i = 0; i < receivedMessages.length; i++) {
            _dispatchMessageFromNative(receivedMessages[i])
        }
    }

    function send(data, responseCallback) {
        _doSend('jsbridge', 'send', data, responseCallback)
    }

    function registerHandler(handlerName, handler) {
        messageHandlers[handlerName] = handler
    }

    function callHandler(handlerName, data, responseCallback) {
        _doSend('jsbridge', handlerName, data, responseCallback)
    }

    function callHandlerWithModule(moduleName, handlerName, data, responseCallback) {
        _doSend(moduleName, handlerName, data, responseCallback)
    }

    function _doSend(moduleName, handlerName, message, responseCallback) {
        var callbackId;
        if (typeof responseCallback === 'string') {
            callbackId = responseCallback
        } else if (responseCallback) {
            callbackId = 'cb_' + (uniqueId++) + '_' + new Date().getTime();
            responseCallbacks[callbackId] = responseCallback
        } else {
            callbackId = ''
        }
        try {
            var evalStr1 = 'window.' + moduleName + '.';
            if (moduleName == 'jsbridge' && handlerName != 'response') {
                evalStr1 += 'handler'
            } else {
                evalStr1 += handlerName
            }
            var fn = eval(evalStr1)
        } catch (e) {
            console.log(e)
        }
        if (typeof fn === 'function') {
            var evalStr = 'window.' + moduleName;
            var fnwindow = eval(evalStr);
            var responseData;
            if (moduleName == 'jsbridge' && handlerName != 'response') {
                responseData = fn.call(fnwindow, handlerName, JSON.stringify(message), callbackId)
            } else {
                responseData = fn.call(fnwindow, JSON.stringify(message), callbackId)
            }
            if (responseData) {
                responseCallback = responseCallbacks[callbackId];
                if (!responseCallback) {
                    return
                }
                responseCallback(responseData);
                delete responseCallbacks[callbackId]
            }
        }
    }

    function _dispatchMessageFromNative(messageJSON) {
        setTimeout(function () {
            var message = JSON.parse(messageJSON);
            var responseCallback;
            if (message.responseId) {
                responseCallback = responseCallbacks[message.responseId];
                if (!responseCallback) {
                    return
                }
                responseCallback(message.responseData);
                delete responseCallbacks[message.responseId]
            } else {
                if (message.callbackId) {
                    var callbackResponseId = message.callbackId;
                    responseCallback = function (responseData) {
                        _doSend('jsbridge', 'response', responseData, callbackResponseId)
                    }
                }
                var handler = WebViewJavascriptBridge._messageHandler;
                if (message.handlerName) {
                    handler = messageHandlers[message.handlerName]
                }
                try {
                    handler(message.data, responseCallback)
                } catch (exception) {
                    if (typeof console != 'undefined') {
                        console.log("WebViewJavascriptBridge: WARNING: javascript handler threw.", JSON.stringify(message), exception)
                    }
                }
            }
        })
    }

    function _handleMessageFromNative(messageJSON) {
        if (receiveMessageQueue) {
            receiveMessageQueue.push(messageJSON)
        }
        _dispatchMessageFromNative(messageJSON)
    }

    var WebViewJavascriptBridge = window.WebViewJavascriptBridge = {
        init: init,
        send: send,
        registerHandler: registerHandler,
        callHandler: callHandler,
        callHandlerWithModule: callHandlerWithModule,
        _handleMessageFromNative: _handleMessageFromNative
    };
    console.log("start jsbridge ...");
    var doc = document;
    var readyEvent = doc.createEvent('Events');
    readyEvent.initEvent('WebViewJavascriptBridgeReady');
    readyEvent.bridge = WebViewJavascriptBridge;
    doc.dispatchEvent(readyEvent);
    console.log("end jsbridge ...")
})();