package com.imageloaderlrucachedemo.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kson on 2017/5/31.
 * 图片加载类
 */

public class ImageLoader {

    //图片缓存
    private ImageCache mImageCache = new MemoryCache();//默认内存缓存
    int mLoadingImageId ;//加载中图片
    int mLoadingFailId;//加载失败图片
    //线程池，数量为cpu的数量
    private ExecutorService mExcutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static ImageLoader mImageLoader = null;

    public ImageCache setImageCache(ImageCache cache) {
        this.mImageCache = cache;

        return mImageCache;
    }

    //单例对象
    public static ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = new ImageLoader();

                }
            }
        }

        return mImageLoader;
    }


    /**
     * 加载图片
     * git
     *
     * @param url       图片url
     * @param imageView 图片控件
     */
    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {

            imageView.setImageBitmap(bitmap);
            return;

        }
        imageView.setImageResource(mLoadingImageId);
        imageView.setTag(url);
        mExcutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImg(url,imageView);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url, bitmap);
            }
        });
    }

    private Bitmap downloadImg(String imageUrl,ImageView imageview) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            if (bitmap==null){
                imageview.setImageResource(mLoadingFailId);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public void cacelThreadExcutors() {
        if (mExcutorService != null) {
            mExcutorService.shutdownNow();
        }
    }


    public void setLoadingImage(int resId){
        mLoadingImageId = resId;
    }

    public void setLoadingFailImage(int resId){
        mLoadingFailId = resId;
    }

}
