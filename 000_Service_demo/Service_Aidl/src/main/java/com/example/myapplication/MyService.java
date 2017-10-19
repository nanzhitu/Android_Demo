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
}
