package com.imageloaderlrucachedemo.Utils;

import android.graphics.Bitmap;

/**
 * Created by kson on 2017/5/31.
 */

public class DoubleCache implements ImageCache{
    ImageCache mImageCache = new MemoryCache();
    DiskCache mDiskCache = new DiskCache();

    //先从内存中获取图片，如果没有，从sdcard获取
    public Bitmap get(String url){
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap==null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和sdcard
    public void put(String url,Bitmap bitmap){
        mImageCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }
}
