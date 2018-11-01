package com.neo.myapplication;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(this.getApplication());
//        FloatUIUtil.getInstance().showView();
        TextView textView = (TextView)findViewById(R.id.tv);
//        AnimationUtil.getInstance().translateAnimation(textView);
        AnimationUtil.getInstance().animationSet(textView);
    }
}
