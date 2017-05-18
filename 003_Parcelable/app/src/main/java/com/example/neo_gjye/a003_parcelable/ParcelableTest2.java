package com.example.neo_gjye.a003_parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ParcelableTest2 extends AppCompatActivity {

    private static final String TAG = ParcelableTest.TAG;
    TextView testview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable_test2);
        Log.d(TAG, "ParcelableTest2");
        testview = (TextView)findViewById(R.id.textView2);
        Person mPerson = (Person)getIntent().getParcelableExtra(ParcelableTest.key);
        testview.setText("name: " + mPerson.getName() + " age: " + mPerson.getAge());
        Serializable_demo text =(Serializable_demo) getIntent().getSerializableExtra("stu");

        Toast.makeText(this,"sno:" + text.getSno() + " sname: " + text.getSname() , Toast.LENGTH_SHORT).show();
    }
}
