package com.oracle.hackthon.common;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by bofan on 2015/1/3.
 */
public class CommonUtils {

    public static String generateName()
    {
        String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)).toString();
        return  name;
    }

    /**
     * 保存图片到本地
     * @param bitmap 图片
     * @param filePath
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, String filePath)
    {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Log.i("TestFile", "SD card is not avaiable/writeable right now.");
            return false;
        }
        FileOutputStream b = null;

        String fileDir = filePath.substring(0,filePath.lastIndexOf("/"));
        File file = new File(fileDir);
        if (!file.exists())
             file.mkdirs();// 创建文件夹
        String fileName = filePath;

        try {
            b = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean saveBitmap(ImageView view, String filePath)
    {

       // view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
      //  view.destroyDrawingCache();
        return saveBitmap(bitmap,filePath);

    }
}
