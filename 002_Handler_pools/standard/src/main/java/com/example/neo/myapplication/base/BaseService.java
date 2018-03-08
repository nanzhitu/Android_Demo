package com.example.neo.myapplication.base;

import android.app.Service;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Mocc on 2018/2/7
 */

public abstract class BaseService extends Service {

    protected MyHandler mHandler = new MyHandler(this);
    protected  String TAG = getClass().getName();

    protected static class MyHandler extends Handler {

        private WeakReference<BaseService> weakReference;

        public MyHandler(BaseService mService) {
            this.weakReference = new WeakReference<BaseService>(mService);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseService mService = weakReference.get();
            if (mService != null) {
                mService.handleMessage(msg);
            }
        }
    }

    protected void handleMessage(Message msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
