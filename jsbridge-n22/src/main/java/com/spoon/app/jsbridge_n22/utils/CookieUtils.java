package com.spoon.app.jsbridge_n22.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

/**
 * Created by gdk on 2020/5/20 10:56
 *
 * @author gdk
 * 设置cookie信息的工具类
 */
public class CookieUtils {
    /**
     * 设置cookie信息
     *
     * @param url:url地址
     * @param cookie:cookie信息
     */
    public static void setCookie(String url, String cookie) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeSessionCookies(null);
            cookieManager.flush();
        } else {
            cookieManager.removeSessionCookie();
            CookieSyncManager.getInstance().sync();
        }
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookie);
    }

    /**
     * 设置cookie信息
     *
     * @param url:url地址
     * @param cookie:cookie信息
     */
    public static void synCookies(String url, String cookie, Context context) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.acceptCookie();
        cookieManager.removeSessionCookie();// 移除
        cookieManager.removeAllCookie();
        /**
         * cookies是在HttpClient中获得的cookie
         */

        cookieManager.setCookie(url, "token=" + cookie);
        /**
         *  判断系统当前版本，同步方式不一样
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.flush();
        } else {
            CookieSyncManager.createInstance(context).sync();
        }

    }

    /**
     * 向页面传输用户信息
     *
     * @param webView
     * @param userInfo
     */
    public static void LocalStorageData(WebView webView, String userInfo) {
        String key = "userInfo";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript("window.localStorage.setItem('" + key + "','" + userInfo + "');", null);
        } else {
            webView.loadUrl("javascript:localStorage.setItem('" + key + "','" + userInfo + "');");
        }
    }
}
