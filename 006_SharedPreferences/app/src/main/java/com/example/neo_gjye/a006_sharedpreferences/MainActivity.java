package com.example.neo_gjye.a006_sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.age);
        textView = (TextView)findViewById(R.id.textView);
        Button write = (Button)findViewById(R.id.write);
        Button read = (Button)findViewById(R.id.read);
        SharedPreferences sharedPreferences = getSharedPreferences("neo", Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit(); //获取编辑器
        editor.putString("name","null");
        editor.putInt("age",0);
        editor.commit();//同步


        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("neo", Context.MODE_APPEND);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //获取编辑器
                String namestr = name.getText().toString();
                int ageint = Integer.parseInt(age.getText().toString());
                editor.putString("name",namestr);
                editor.putInt("age",ageint);
                //editor.commit();//同步
                editor.apply();//异步
                Toast.makeText(MainActivity.this,"保存成功！",Toast.LENGTH_SHORT).show();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("neo", Context.MODE_APPEND);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //获取编辑器
                String name_read = sharedPreferences.getString("name", "fuck");
                int age_read = sharedPreferences.getInt("age", 1);
                textView.setText("姓名： " + name_read + ", 年龄： " + age_read);
            }
        });
    }
}
