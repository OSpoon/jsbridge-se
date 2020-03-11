package com.spoon.app.jsbridge_n22.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.spoon.app.jsbridge_n22.bean.Result;

public class ResultUtil {

    public static String success(Object content) {
        Result result = new Result();
        result.setContent(content);
        result.setError(null);
        return new Gson().toJson(result);
    }

    public static String error(String code, String message) {
        if (TextUtils.isEmpty(code)) {
            code = "1";
        }
        if (TextUtils.isEmpty(message)) {
            message = "error";
        }
        Result result = new Result();
        result.setContent(message);
        result.setError(code);
        return new Gson().toJson(result);
    }
}
