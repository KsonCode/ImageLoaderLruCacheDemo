package com.imageloaderlrucachedemo.Utils;

import android.graphics.Bitmap;

/**
 * 缓存接口
 */

public interface ImageCache {
    void put(String url,Bitmap bmp);
    Bitmap get(String url);
}
