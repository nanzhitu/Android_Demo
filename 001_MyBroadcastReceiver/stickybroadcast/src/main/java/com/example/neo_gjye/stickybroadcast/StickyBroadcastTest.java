package com.example.neo_gjye.stickybroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StickyBroadcastTest extends AppCompatActivity {
    Button mSendBroadcast;
    Button mSendStickyBroadcast;
    Button mNextActivity;

    private int mStickyBrcCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_broadcast_test);
        mSendBroadcast = (Button)findViewById(R.id.sbBtn);
        mSendStickyBroadcast = (Button)findViewById(R.id.ssBtn);
        mNextActivity = (Button)findViewById(R.id.naBtn);

        mSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.android.action.broadcast");
                sendBroadcast(intent);
            }
        });

        mSendStickyBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStickyBrcCount++;
                Intent intent = new Intent("com.android.action.sticky.broadcast");
                intent.putExtra("send_count",mStickyBrcCount);
                sendStickyBroadcast(intent);
            }
        });

        mNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StickyBroadcastTest.this,MyReceiverActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStickyBrcCount = 0;
    }
}
