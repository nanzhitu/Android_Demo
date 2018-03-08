package com.example.neo.myapplication.utils;

import android.util.Log;



/**
 * Created by Mocc on 2018/1/3
 */

public class LogUtils {

    private static boolean LOGOUT = true;
    private final static String TAG = LogUtils.class.getName() + ":";

    public static void d(String tag, String msg) {
        if (LOGOUT) {
            Log.d(TAG + tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOGOUT) {
            Log.e(TAG + tag, msg);
        }
    }

}
