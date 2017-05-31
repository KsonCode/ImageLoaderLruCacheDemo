package com.imageloaderlrucachedemo.Utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by qingchen on 2017/5/31.
 */

public class CloseUtils  {

    private CloseUtils(){}

    public static void closeQuitly(Closeable closeable){
        if (null!=closeable){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
