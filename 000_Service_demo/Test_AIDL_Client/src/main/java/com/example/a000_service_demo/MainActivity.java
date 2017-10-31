package com.example.a000_service_demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    Button button;
    private IMyAidlInterface myInterface;
    public final static String TAG = "MainActivity";

    private ServiceConnection connection = new ServiceConnection() {

        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service建立关联和解除关联的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        //在Activity与Service建立关联时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //IMyAidlInterface.Stub.asInterface()方法将传入的IBinder对象传换成了myInterface对象
            myInterface = IMyAidlInterface.Stub.asInterface(service);

            try {

                //通过该对象调用在IMyAidlInterface.aidl文件中定义的接口方法,从而实现跨进程通信
                String s = myInterface.getInfor("我是Activity传来的字符串");
                Log.i(TAG, "从Service得到的字符串：" + s);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "准备绑定远程service");
                Intent intent = new Intent("com.example.myapplication.AIDL_Service");
                intent.setPackage("com.example.myapplication");
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });
    }
}
