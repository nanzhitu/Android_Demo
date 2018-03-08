package com.example.neo.myapplication.service;

import android.content.Intent;
import android.os.IBinder;
import android.os.Message;

import com.example.neo.myapplication.base.BaseService;

public class Service0 extends BaseService {

    public Service0() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case 0:

                break;
            case 1:

                break;
            default:
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
