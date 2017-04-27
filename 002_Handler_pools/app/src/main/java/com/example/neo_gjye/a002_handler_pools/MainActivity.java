package com.example.neo_gjye.a002_handler_pools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button beginBtn;
    EditText editText;
    TextView textView;
    private Message messageThread;
    private MyThread myThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beginBtn = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.text);

        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(editText.getText().toString());
                Message message = new Message();
                message.what = 123;
                Bundle bundle = new Bundle();
                bundle.putInt("num",num);
                message.setData(bundle);
                myThread.mHandler.sendMessage(message);

            }
        });

        myThread = new MyThread();
        myThread.start();
    }

    //创建主线程的handler
    private Handler mainHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 321) {
                List<Integer> main_num = msg.getData().getIntegerArrayList("list");
                textView.setText(main_num.toString());
            }
        };

    };
    class MyThread extends Thread{
        public Handler mHandler;
        private List<Integer> nums = new ArrayList<Integer>();

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what == 123){
                          nums.removeAll(nums);
                        int num = msg.getData().getInt("num");
                        outer:
                            for(int i = 2; i < num; i++) {
                                for (int j = 2; j <= Math.sqrt(i); j++)
                                    if (i != 2 && i % j == 0)
                                        continue outer;
                                nums.add(i);
                            }
                        Toast.makeText(MainActivity.this, nums.toString(), Toast.LENGTH_LONG).show();
                        messageThread = new Message();
                        messageThread.what = 321;
                        Bundle bundle = new Bundle();
                        bundle.putIntegerArrayList("list",(ArrayList<Integer>)nums);
                        messageThread.setData(bundle);
                        mainHandler.sendMessage(messageThread);
                        //textView.setText(nums.toString());//error need origin thread

                    }

                }
            };
            Looper.loop();
        }
    }
}
