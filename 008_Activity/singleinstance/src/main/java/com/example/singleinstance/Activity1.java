package com.example.singleinstance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity1 extends AppCompatActivity implements View.OnClickListener {

    private Button a1,a2,a3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        Log.d("SingleInstance", "Activity1的栈ID："+getTaskId()+"");

        a1 = (Button)findViewById(R.id.a1);
        a2 = (Button)findViewById(R.id.a2);
        a3 = (Button)findViewById(R.id.a3);
        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.a1:
                startActivity(new Intent(this,Activity1.class));
                break;
            case R.id.a2:
                startActivity(new Intent(this,Activity2.class));
                break;
            case R.id.a3:
                startActivity(new Intent(this,Activity3.class));
                break;
        }
    }
}
