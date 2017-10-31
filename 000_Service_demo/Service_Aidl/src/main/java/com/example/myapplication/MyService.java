package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
public class MyService extends Service {

    public final static String TAG = "MyService";

    public MyService() {
    }

    private IBinder binder = new IMyAidlInterface.Stub(){

        @Override
        public String getInfor(String s) throws RemoteException {
            Log.i(TAG, s);
            return "我是 Service 返回的字符串";
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String s = "执行了onCreat()";
        Log.i(TAG, s);
        System.out.println();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String s = "执行了onStartCommand()";
        Log.i(TAG, s);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        String s = "执行了onDestory()";
        Log.i(TAG, s);
    }
}
