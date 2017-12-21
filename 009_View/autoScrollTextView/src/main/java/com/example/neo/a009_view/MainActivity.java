package com.example.neo.a009_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextSwitchView tsv = (TextSwitchView) findViewById(R.id.tsv);
        String[]  res={
                "静夜思",
                "床前明月光",
                "疑是地上霜",
                "举头望明月",
                "低头思故乡"
        };

        tsv.setResources(res);
        tsv.setTextStillTime(4000);
    }
}
