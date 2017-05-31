package com.imageloaderlrucachedemo.Utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by ksen on 2017/5/31.
 * 图片缓存类 使用Lrucache算法进行缓存 内存缓存
 */

public class ImageCache {
    //图片 LRU 缓存
    private LruCache<String, Bitmap> mImageCache;

    public ImageCache() {
        initImageCache();
    }

    /**
     * 初始化
     */
    private void initImageCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);//以kb为单位
        final int cacheSize = maxMemory / 8;

        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;//重新计算内存
            }
        };
    }

    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String url) {
        mImageCache.get(url);
        return null;
    }
}
