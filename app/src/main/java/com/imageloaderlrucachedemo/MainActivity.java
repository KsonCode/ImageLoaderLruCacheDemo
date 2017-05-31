package com.imageloaderlrucachedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import com.imageloaderlrucachedemo.Utils.ImageLoader;
import com.imageloaderlrucachedemo.Utils.Images;
import com.imageloaderlrucachedemo.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {
    private GridView mGv;
    private MyAdapter mMyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {

        mGv = (GridView) findViewById(R.id.gv);
        mMyAdapter = new MyAdapter(this,0, Images.imageThumbUrls,mGv);
        mGv.setAdapter(mMyAdapter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoader mImageLoader = ImageLoader.getImageLoader();
        mImageLoader.cacelThreadExcutors();
    }
}
