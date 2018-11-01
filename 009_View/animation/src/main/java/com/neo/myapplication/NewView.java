package com.neo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

/**
 * Created by Neo on 2018/10/31.
 */

public class NewView extends RelativeLayout {

    private static final String TAG = "NewView";
    Context mContext;

    public NewView(Context context) {
        super(context);
        mContext = context;
    }

    public NewView(Context context, AttributeSet attributes){
        super(context,attributes);
        mContext = context;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG,"onKeyDown keycode = "+event.getKeyCode());

        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            FloatUIUtil.getInstance().dismissView();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
