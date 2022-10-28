package com.spoon.app.jsbridge_se.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : zhangxin
 * date : 2020-03-18 15:57
 * description : 插件名称注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BridgePlugin {
    String name();
}
