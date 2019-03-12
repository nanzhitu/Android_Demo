package com.neo.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * Created by Neo on 2019/3/12.
 */

public class MyLinearLayoutManager extends LinearLayoutManager {

    private static final String TAG = "MyLinearLayoutManager";

    public MyLinearLayoutManager(Context context) {
        super(context);
    }


    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout){
        super(context,orientation,reverseLayout);
    }

    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
        //在实际项目中出现当position等于显示屏最后一项时，再往下会跳到第一项的问题
        //也就是RecyclerView常见的焦点乱飞问题
        //这里root cause 是每个item都要进行一顿操作，导致新item还没准备好而乱飞
        //解决办法如下，强制跳到新item
        int currentPosition= getPosition(getFocusedChild());
        int count = getItemCount();
        int lastVisiblePosition = findLastVisibleItemPosition();
        Log.d(TAG,"currentPosition = "+currentPosition+" , direction"+direction);
        Log.d(TAG,"count = "+count+" , lastVisiblePosition"+lastVisiblePosition);

        switch (direction){
            case View.FOCUS_DOWN:
                currentPosition++;
                break;
            case View.FOCUS_UP:
                currentPosition--;
                break;
        }
        if(currentPosition <0 || currentPosition > count){
            return focused;
        }else{
            if(currentPosition > lastVisiblePosition){
                scrollToPosition(currentPosition);
            }
        }
        return super.onInterceptFocusSearch(focused, direction);
    }
}

