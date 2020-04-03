package com.spoon.app.jsbridge_n22.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * author : zhangxin
 * date : 2020-04-02 23:40
 * description :
 */
public class Utils {

    /**
     * Convert our DIP units to Pixels
     *
     * @return int
     */
    public static int dpToPixels(Activity activity, int dipValue) {
        int value = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                (float) dipValue,
                activity.getResources().getDisplayMetrics()
        );
        return value;
    }

    public static int hexStringToColor(String hex) {
        int result = 0;

        if (hex != null && !hex.isEmpty()) {
            if (hex.charAt(0) == '#') {
                hex = hex.substring(1);
            }

            // No alpha, that's fine, we will just attach ff.
            if (hex.length() < 8) {
                hex += "ff";
            }

            result = (int) Long.parseLong(hex, 16);

            // Almost done, but Android color code is in form of ARGB instead of
            // RGBA, so we gotta shift it a bit.
            int alpha = (result & 0xff) << 24;
            result = result >> 8 & 0xffffff | alpha;
        }

        return result;
    }

    /**
     * This is a rather unintuitive helper method to load images. The reason why this method exists
     * is because due to some service limitations, one may not be able to add images to native
     * resource bundle. So this method offers a way to load image from www contents instead.
     * However loading from native resource bundle is already preferred over loading from www. So
     * if name is given, then it simply loads from resource bundle and the other two parameters are
     * ignored. If name is not given, then altPath is assumed to be a file path _under_ www and
     * altDensity is the desired density of the given image file, because without native resource
     * bundle, we can't tell what density the image is supposed to be so it needs to be given
     * explicitly.
     */
    public static Drawable getImage(Activity activity, String name, String altPath, double altDensity) throws IOException {
        Drawable result = null;
        Resources activityRes = activity.getResources();

        if (name != null) {
            int id = activityRes.getIdentifier(name, "drawable",
                    activity.getPackageName());
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                result = activityRes.getDrawable(id);
            } else {
                result = activityRes.getDrawable(id, activity.getTheme());
            }
        } else if (altPath != null) {
            File file = new File("www", altPath);
            InputStream is = null;
            try {
                is = activity.getAssets().open(file.getPath());
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                bitmap.setDensity((int) (DisplayMetrics.DENSITY_MEDIUM * altDensity));
                result = new BitmapDrawable(activityRes, bitmap);
            } finally {
                // Make sure we close this input stream to prevent resource leak.
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
        return result;
    }

    public static String getAppMetaKey(Context context,String metaKey) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai)
                metaData = ai.metaData;
            if (null != metaData) {
                // MY_META_KEY是meta-data中对应的key值
                appKey = metaData.getString(metaKey);
                return appKey;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return appKey;
    }
}
