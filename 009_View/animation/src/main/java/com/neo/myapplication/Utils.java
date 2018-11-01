package com.neo.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * Created by admin on 2018/10/31.
 */

public class Utils {


    private static Context sContext;
    private static Handler sHandler;
    private Utils() {}


    /**
     * 初始化工具类
     *
     * @param app 应用
     */
    public static void init(@NonNull final Application app) {
        sContext = app;
        sHandler = new Handler(Looper.getMainLooper());

    }

    /**
     * 去初始化工具类
     *
     */
    public static void unInit() {
        sHandler = null;
        sContext = null;
    }

    /**
     * 获取 Application
     *
     * @return Context
     */
    public static Context getAppContext() {
        if (sContext != null) return sContext;
        throw new NullPointerException("u should init first");
    }


    /**
     * 获取 Handler
     *
     * @return Handler
     */
    public static Handler getAppHandler(){
        if (sHandler != null) return sHandler;
        throw new NullPointerException("u should init first");
    }
}
