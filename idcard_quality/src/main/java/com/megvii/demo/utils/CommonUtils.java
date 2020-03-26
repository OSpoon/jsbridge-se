package com.megvii.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.megvii.demo.bo.Constant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class CommonUtils {


    /**
     * 把dp根据当前屏幕密度转换成px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 保存bitmap至指定Picture文件夹
     */
    public static String saveBitmap(Context mContext, Bitmap bitmaptosave, String fileName) {
        if (bitmaptosave == null)
            return null;

        File mediaStorageDir = mContext
                .getExternalFilesDir(Constant.CACHEIMAGE);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // String bitmapFileName = System.currentTimeMillis() + ".jpg";
//		String bitmapFileName = System.currentTimeMillis() + "";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mediaStorageDir + "/" + fileName);
            boolean successful = bitmaptosave.compress(
                    Bitmap.CompressFormat.JPEG, 100, fos);

            if (successful)
                return mediaStorageDir.getAbsolutePath() + "/" + fileName;
            else
                return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void toggleHideyBar(Activity activity) {
        int uiOptions = activity.getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;


        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        if (Build.VERSION.SDK_INT >= 19) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        activity.getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    public static byte[] bmp2byteArr(Bitmap bmp) {
        if (bmp == null || bmp.isRecycled())
            return null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,90, byteArrayOutputStream);
        if (byteArrayOutputStream != null) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {

            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
