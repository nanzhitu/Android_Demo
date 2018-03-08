package com.example.neo.myapplication.activity;

import android.os.Message;
import android.widget.TextView;

import com.example.neo.myapplication.R;
import com.example.neo.myapplication.base.BaseActivity;

public class Activity0 extends BaseActivity {

    TextView textView;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void init() {
        mHandler.sendEmptyMessage(0);
        test();
    }

    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                textView.setText("message0 success");
                break;
            case 1:

                break;
            default:
                break;
        }
    }


    private void test(){
        Message message = new Message();
        message.what = 0;
        mHandler.sendMessage(message);
    }
}
