package com.imageloaderlrucachedemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.imageloaderlrucachedemo.R;
import com.imageloaderlrucachedemo.Utils.ImageCache;
import com.imageloaderlrucachedemo.Utils.ImageLoader;

/**
 * Created by kson on 2017/5/31.
 */

public class MyAdapter extends ArrayAdapter<String> {

    private GridView mGridView;//Gridview 实例

    private ImageLoader mImageLoader;

    /**
     * 第一张可见图片的下标
     */
    private int mFirstVisibleItem;

    /**
     * 一屏有多少张图片可见
     */
    private int mVisibleItemCount;

    /**
     * 记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片的问题。
     */
    private boolean isFirstEnter = true;

    public MyAdapter(Context context, int textViewResourceId, String[] objects,
                     GridView gv) {
        super(context, textViewResourceId, objects);
        mGridView = gv;

        mImageLoader = ImageLoader.getImageLoader();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String url = getItem(position);


        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.img_item, null);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        iv.setTag(url);

        setImageView(url, iv);

        return convertView;
    }

    /**
     * 设置图片，从缓存获取图片，如果没有则默认图
     *
     * @param url
     * @param iv
     */
    private void setImageView(String url, ImageView iv) {
        ImageCache imageCache = new ImageCache();
        Bitmap bitmap = imageCache.get(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        } else {
//            iv.setImageResource(R.mipmap.ic_launcher);
            mImageLoader.displayImage(url, iv);
        }
    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//        if (scrollState == SCROLL_STATE_IDLE) {
//            for (int i = mFirstVisibleItem; i < mFirstVisibleItem + mVisibleItemCount; i++) {
//                mImageLoader.displayImage(Images.imageThumbUrls[i], (ImageView) mGridView.findViewWithTag(Images.imageThumbUrls[i]));
//            }
//        }
////        else {
////            mImageLoader.cacelThreadExcutors();
////        }
//
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        mFirstVisibleItem = firstVisibleItem;
//        mVisibleItemCount = visibleItemCount;
//        // 下载的任务应该由onScrollStateChanged里调用，但首次进入程序时onScrollStateChanged并不会调用，
//        // 因此在这里为首次进入程序开启下载任务。
//        if (isFirstEnter && visibleItemCount > 0) {
//            for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
//
//                mImageLoader.displayImage(Images.imageThumbUrls[i], (ImageView) mGridView.findViewWithTag(Images.imageThumbUrls[i]));
//            }
//        }
//    }


}
