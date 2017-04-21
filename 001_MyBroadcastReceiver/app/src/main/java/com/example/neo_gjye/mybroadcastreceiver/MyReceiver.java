package com.example.neo_gjye.mybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        String name = intent.getExtras().getString("name");
        Toast.makeText(context,"广播0已发送。。。" + name , Toast.LENGTH_SHORT).show();


        // an Intent broadcast.
    //    throw new UnsupportedOperationException("Not yet implemented");
    }
}
