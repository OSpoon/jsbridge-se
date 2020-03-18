package com.spoon.app.jsbridge_n22.core;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author smallbuer
 * bridge manager
 */
public enum Bridge {

    INSTANCE;

    private String TAG = "Bridge";
    private Boolean DEBUG = false;
    private Map<String, BridgeHandler> mMessageHandlers = new HashMap<>();

    public Boolean getDEBUG() {
        return DEBUG;
    }

    public void openLog(){
        this.DEBUG = true;
    }


    /**
     * global all handlers
     * @return
     */
    public Map<String, BridgeHandler> getMessageHandlers(){
        return mMessageHandlers;
    }

    /**
     * register handler,so that javascript can call it
     * 注册处理程序,以便javascript调用它
     *
     * @param handlerName handlerName
     * @param handler     BridgeHandler
     */
    public void registerHandler(String handlerName, BridgeHandler handler) {
        if (handler != null) {
            // 添加至 Map<String, BridgeHandler>
            mMessageHandlers.put(handlerName, handler);
        }
    }


    /**
     * register handler,so that javascript can call it
     * 注册处理程序,以便javascript调用它
     *
     * @param handlers handlerName
     */
    public void registerHandler(Map<String, BridgeHandler> handlers) {
        if (handlers != null) {
            mMessageHandlers.putAll(handlers);
        }
    }

    /**
     * register handler,so that javascript can call it
     * 注册处理程序,以便javascript调用它
     *
     * @param pluginClass
     * @return
     */
    public void registerHandler(Class<?>... pluginClass) {
        Map<String, BridgeHandler> map = new HashMap<>();
        for (Class<?> handlerClass : pluginClass) {
            for (Constructor<?> constructor : handlerClass.getConstructors()) {
                try {
                    Object plugin = constructor.newInstance();
                    if (plugin instanceof BridgeHandler) {
                        BridgeHandler bridgeHandler = (BridgeHandler) plugin;
                        String pluginName = getPluginName(bridgeHandler);
                        if (!TextUtils.isEmpty(pluginName)) {
                            map.put(pluginName, bridgeHandler);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        if (map.size() > 0) {
            registerHandler(map);
        } else {
            Log.e("Bridge", "无插件注册");
        }
    }

    /**
     * 获取插件名称
     *
     * @param bridgeHandler
     * @return
     */
    private static String getPluginName(BridgeHandler bridgeHandler) {
        Class<? extends BridgeHandler> clazz = bridgeHandler.getClass();
        BridgePlugin annotation = clazz.getAnnotation(BridgePlugin.class);
        if (annotation != null) {
            return annotation.name();
        }
        return null;
    }

    /**
     * unregister handler
     *
     * @param handlerName
     */
    public void unregisterHandler(String handlerName) {
        if (handlerName != null) {
            mMessageHandlers.remove(handlerName);
        }
    }

}
