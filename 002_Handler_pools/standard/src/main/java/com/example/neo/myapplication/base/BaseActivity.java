package com.example.neo.myapplication.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.neo.myapplication.utils.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Created by Mocc on 2018/1/3
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getName();
    public BaseActivity activity;
    protected MyHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activity = this;
        mHandler = new MyHandler(this);

        initView();
        initListener();
        init();

    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void init();

    protected static class MyHandler extends Handler {

        private WeakReference<BaseActivity> weakReference;

        public MyHandler(BaseActivity activity) {
            this.weakReference = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity mActivity = weakReference.get();
            if (mActivity != null) {
                BaseActivity activity = weakReference.get();
                if (activity != null && !activity.isFinishing()) {
                    activity.handleMessage(msg);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 如果需要用到mHandler发送消息，需要子类重载该函数
     *
     * @param msg
     */
    protected void handleMessage(Message msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG, "onDestroy");
        if (mHandler != null) {
            mHandler.weakReference.clear();
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
