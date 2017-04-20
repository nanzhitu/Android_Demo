package com.example.neo_gjye.service_demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartService extends Service {
    private static final String TAG = "ServiceDemo" ;
    public static final String ACTION = "com.example.neo_gjye.service_demo.StartService";
    public StartService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.v(TAG, "ServiceDemo onBind");
        Service_main.text = Service_main.text + "\n"+"onBind";
        return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "ServiceDemo onUnBind");
        Service_main.text = Service_main.text + "\n"+"onUnBind";
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate(){
        Log.v(TAG, "ServiceDemo onCreate");
        Service_main.text = Service_main.text + "\n"+"onCreate";
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "ServiceDemo onStartCommand");
        Service_main.text = Service_main.text + "\n"+"onStartCommand";
        return super.onStartCommand(intent, flags, startId);
    }
}
