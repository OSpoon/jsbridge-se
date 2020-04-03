package com.ospoon.app.sunlife.jsbridge_plugins_n22.ImgUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.DateFormat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * 图片压缩工具
 * Created by gdk on 2020/04/03 9:04
 *
 * @author gdk
 */
public class ImgUtils {
    /**
     * 得到压缩之后的图片
     *
     * @param name
     * @param imgPath
     * @return
     */
    public static ArrayList<String> getCompressImg(String name, String imgPath) {
        ArrayList<String> resList = new ArrayList<>();
        if (!TextUtils.isEmpty(imgPath)) {
            if (imgPath.contains(",")) {
                String[] imgRes = imgPath.split(",");
                for (String item : imgRes) {
                    Bitmap bitmap = BitmapFactory.decodeFile(item);
//                    Bitmap bitmap = ImageUtil.decodeSampledBitmapFromFile(item, 720, 1080);
                    if (bitmap != null) {
                        String imagePath = compressImage(bitmap, name, getImagePath().getPath());
                        bitmap.recycle();
                        bitmap = null;

                        if (!TextUtils.isEmpty(imagePath)) {
                            resList.add(imagePath);
                        }
                    }
                }
            } else {
                Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
//                Bitmap bitmap = ImageUtil.decodeSampledBitmapFromFile(imgPath, 720, 1080);
                if (bitmap != null) {
                    String imagePath = compressImage(bitmap, name, getImagePath().getPath());
                    bitmap.recycle();
                    bitmap = null;

                    if (!TextUtils.isEmpty(imagePath)) {
                        resList.add(imagePath);
                    }
                }
            }
        }
        return resList;
    }

    /**
     * 质量压缩方法
     *
     * @param
     * @return
     */
    public static String compressImage(Bitmap image, String addName, String path) {

        BitmapFactory.Options bitmapoptions = new BitmapFactory.Options();
        bitmapoptions.inJustDecodeBounds = false;
//        bitmapoptions.inSampleSize = calculateInSampleSize(image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options =100;
        while (baos.toByteArray().length / 1024 > 1024) { // 循环判断如果压缩后图片是否大于1024kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

        String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".png";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();// 创建文件夹
        }
        String fileName = path + File.separator;
        if (!TextUtils.isEmpty(addName)) {
            fileName += addName + "_";
        }
        fileName += name;
        File myCaptureFile = new File(fileName);

        try {
            FileOutputStream fos = new FileOutputStream(myCaptureFile);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        recycleBitmap(bitmap);
//        BufferedOutputStream bos = null;
//        try {
//            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//        try {
//            bos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            bos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return myCaptureFile.getAbsolutePath();
    }

    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps == null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }

    /**
     * 计算图片的大小
     *
     * @param bitmap
     * @return
     */
    private static int calculateInSampleSize(Bitmap bitmap) {

        int inSampleSize = 1;
        //File file = new File(imagepath);
        if (bitmap.getByteCount() > 3024000) {
            inSampleSize = 7;
            return inSampleSize;
        } else if (bitmap.getByteCount() > 1024000) {
            inSampleSize = 5;
            return inSampleSize;
        } else if (bitmap.getByteCount() > 502400) {
            inSampleSize = 3;
            return inSampleSize;
        } else if (bitmap.getByteCount() > 102400) {
            inSampleSize = 2;
            return inSampleSize;
        }

        return inSampleSize;
    }

    public static File getImagePath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    }
}
