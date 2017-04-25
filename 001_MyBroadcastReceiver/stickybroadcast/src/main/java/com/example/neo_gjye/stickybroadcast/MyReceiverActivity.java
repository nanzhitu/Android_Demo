package com.example.neo_gjye.stickybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MyReceiverActivity extends AppCompatActivity {
    IntentFilter mintentFilter;
    TextView text;
   public static String text_str = "action";
    private final static String TAG = "MyReceiverActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_receiver);
        mintentFilter  = new IntentFilter();
        mintentFilter.addAction("com.android.action.broadcast");
        mintentFilter.addAction("com.android.action.sticky.broadcast");
        text = (TextView)findViewById(R.id.text);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            int count = intent.getIntExtra("send_count",-1);
            Log.d(TAG, "action = " + action + "and count = " + count);
            text_str = "action = " + action + "and count = " + count;
            //removeStickyBroadcast(intent);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver,mintentFilter);
        text.setText(text_str);
    }
}
