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
    private ImageCache mImageCache = new ImageCache();
    //线程池，数量为cpu的数量
    private ExecutorService mExcutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//    private ExecutorService mExcutorService = Executors.newSingleThreadExecutor();

    private static ImageLoader mImageLoader = null;

    private ImageLoader() {
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
     *git
     * @param url       图片url
     * @param imageView 图片控件
     */
    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {

            imageView.setImageBitmap(bitmap);
            return;

        }
        imageView.setTag(url);
        mExcutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImg(url);
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

    private Bitmap downloadImg(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public  void cacelThreadExcutors(){
        if (mExcutorService!=null){
                mExcutorService.shutdownNow();
            }
        }
}
