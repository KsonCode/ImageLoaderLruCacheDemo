package com.imageloaderlrucachedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.imageloaderlrucachedemo.Utils.ImageLoader;

public class MainActivity extends AppCompatActivity {
    private ImageView mIv1, mIv2, mIv3;
    private ImageLoader mImageLoader;
    private String[] urls = {"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2056231335,4212597522&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3071269338,4132538973&fm=11&gp=0.jpg", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4033006514,1344445694&fm=11&gp=0.jpg"};//测试数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        showImgs();
    }

    private void initViews() {

        mIv1 = (ImageView) findViewById(R.id.img1);
        mIv2 = (ImageView) findViewById(R.id.img2);
        mIv3 = (ImageView) findViewById(R.id.img3);

    }

    private void showImgs() {
        mImageLoader = ImageLoader.getImageLoader();

        mImageLoader.displayImage(urls[0], mIv1);
        mImageLoader.displayImage(urls[1], mIv2);
        mImageLoader.displayImage(urls[2], mIv3);

    }
}
