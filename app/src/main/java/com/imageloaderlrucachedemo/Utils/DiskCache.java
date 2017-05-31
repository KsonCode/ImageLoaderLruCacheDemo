package com.imageloaderlrucachedemo.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kson on 2017/5/31.
 */

public class DiskCache implements ImageCache{
    static String cacheDir = "sdcard/cache/";

    //从缓存中获取图片
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(cacheDir+url);
    }

    /**
     * 将图片缓存到内存中
     * @param url
     * @param bitmap
     */
    public void put(String url,Bitmap bitmap){

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir+url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
//            if (fileOutputStream!=null){
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            CloseUtils.closeQuitly(fileOutputStream);//关闭资源
        }

    }
}
