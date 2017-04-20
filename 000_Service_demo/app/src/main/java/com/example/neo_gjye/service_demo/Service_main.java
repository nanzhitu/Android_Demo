package com.example.neo_gjye.service_demo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Service_main extends AppCompatActivity {
    private static final String TAG = "Service_mainActivity";
    public static  String text = "11111";
    Button bindBtn;
    Button unbindBtn;
    Button startBtn;
    Button stopBtn;
    Button sleepBtn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);

        bindBtn = (Button)findViewById(R.id.bindBtn);
        startBtn = (Button)findViewById(R.id.startBtn);
        stopBtn = (Button)findViewById(R.id.startBtn);
        unbindBtn = (Button)findViewById(R.id.unbindBtn);
        sleepBtn = (Button)findViewById(R.id.textBtn);
        textView = (TextView)findViewById(R.id.textView) ;
        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(Service_main.this, StartService.class),conn,BIND_AUTO_CREATE);
                textView.setText(text);

            }
        });

        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =  new Intent();
                intent.setClass(Service_main.this, StartService.class);
                unbindService(conn);
                textView.setText(text);

            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(Service_main.this,StartService.class));
                textView.setText(text);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(Service_main.this,StartService.class));
                textView.setText(text);
            }
        });

        sleepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(Service_main.this,MyIntentService.class));
            }
        });
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(TAG,"onServiceConnected");
            text = text + "\n"+"onServiceConnected";

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG,"onServiceDisconnected");
            text = text + "\n"+"onServiceDisconnected";
        }
    };


    @Override
    protected void onDestroy() {
        Log.v(TAG,"onDestroy unbindService");
        text = text + "\n"+"onDestroy unbindService";
        unbindService(conn);
        super.onDestroy();
    }
}
