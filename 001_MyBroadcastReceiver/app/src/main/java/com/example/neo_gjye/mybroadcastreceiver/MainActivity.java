package com.example.neo_gjye.mybroadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button sendBtn;
    Button orderBtn;
    Button localBtn;
    LocalBroadcastReceiver localReceiver;
    LocalBroadcastManager lbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn = (Button)findViewById(R.id.sendBtn);
        orderBtn = (Button)findViewById(R.id.orderBtn);
        localBtn = (Button)findViewById(R.id.localBtn);
        localReceiver = new LocalBroadcastReceiver();
        lbm = LocalBroadcastManager.getInstance(MainActivity.this);
        lbm.registerReceiver(localReceiver,new IntentFilter("localReceiver"));

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("myaction");
                intent.putExtra("name","fish");
                sendBroadcast(intent);
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("myaction");
                intent.putExtra("name","fish");
                sendOrderedBroadcast(intent,null);
            }
        });

        localBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("localReceiver");
                intent.putExtra("name","fish4");
                lbm.sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lbm.unregisterReceiver(localReceiver);
    }
}
