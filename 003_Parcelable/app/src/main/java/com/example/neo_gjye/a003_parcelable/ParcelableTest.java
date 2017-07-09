package com.example.neo_gjye.a003_parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ParcelableTest extends AppCompatActivity implements OnClickListener {

    public static final String TAG = "Parcelable";
    public static final String key = "key";
    Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable_test);
        testBtn = (Button)findViewById(R.id.button);
        testBtn.setOnClickListener(this);
        Log.d(TAG, "ParcelableTest");
    }

    private void fun(){
        Log.d(TAG, "fun");
        Person mPerson = new Person();
        mPerson.setName("neo");
        mPerson.setAge(24);

        Serializable_demo sd = new Serializable_demo();
        sd.setSno(123);
        sd.setSname("neo-gj.ye");

        Intent mIntent = new Intent(this,ParcelableTest2.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(key, mPerson);
        mIntent.putExtras(mBundle);
        mIntent.putExtra("stu",sd);
        startActivity(mIntent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                fun();
                break;
            default:
                break;
        }
    }
}
