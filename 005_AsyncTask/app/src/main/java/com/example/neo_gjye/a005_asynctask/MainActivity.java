package com.example.neo_gjye.a005_asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button03);
        textView = (TextView)findViewById(R.id.textView01);
        progressBar = (ProgressBar)findViewById(R.id.progressBar02);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBarAsyncTask asyncTask = new ProgressBarAsyncTask(textView,progressBar);
                asyncTask.execute(2000);
            }
        });
    }
}
