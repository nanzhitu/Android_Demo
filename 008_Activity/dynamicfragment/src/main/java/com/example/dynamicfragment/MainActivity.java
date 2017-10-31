package com.example.dynamicfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button add1,add2,remove2,replace1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add1 = (Button)findViewById(R.id.button);
        add2 = (Button)findViewById(R.id.button2);
        remove2 = (Button)findViewById(R.id.button3);
        replace1 = (Button)findViewById(R.id.button4);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1 fragment1 = new Fragment1();
                addFragment(fragment1,"fragment1");
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 fragment2 = new Fragment2();
                addFragment(fragment2,"fragment2");
            }
        });

        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                Fragment fragment = manager.findFragmentByTag("fragment2");
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(fragment);
                transaction.commit();

            }
        });

        replace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                Fragment2 fragment2 = new Fragment2();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();//这里会把fragment_container清空，接着放入2
            }
        });
    }

    private void addFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, tag);
        transaction.commit();
    }
}
