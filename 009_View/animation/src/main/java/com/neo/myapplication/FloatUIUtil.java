package com.neo.myapplication;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by admin on 2018/10/31.
 */

public class FloatUIUtil {

    private static final String TAG = "FloatUIUtil";
    private View mView;
    private boolean isShowView;
    private static WindowManager mWindowManager;
    private static Handler mHandler;

    private static Singleton<FloatUIUtil> sSingleton;

    private FloatUIUtil() {
    }

    public static FloatUIUtil getInstance(){
        if(sSingleton == null){
            sSingleton = new Singleton<FloatUIUtil>() {
                @Override
                protected FloatUIUtil createInstance() {
                    return new FloatUIUtil();
                }
            };
        }
        return sSingleton.getInstance();
    }


    public void showView() {
        if (!isShowView) {
            isShowView = true;
            Utils.getAppHandler().post(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "showView");

                    Context context = Utils.getAppContext();
                    int mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
                    int mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
                    float scale=context.getResources().getDisplayMetrics().density;
                    Log.d(TAG, "showView  scale = "+scale);
                    mView = LayoutInflater.from(context).inflate(R.layout.view_newmsg, null);
                    createFloatView(context, mView, 0, 0, mScreenWidth, mScreenHeight, 0, true, -1,WindowManager.LayoutParams.TYPE_TOAST);
                    NewView newView = (NewView) mView.findViewById(R.id.view_newmsg);

                    View view =  newView.findViewById(R.id.ll_newmsg);
                    AnimationUtil.getInstance().translateUpDown(view,-66,33,null);
                }
            });
        }
    }

    public void dismissView() {
        if (mView != null) {
            Utils.getAppHandler().post(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "dismissView");
                    if (mView != null) {
                        removeFloatView(mView);
                        mView = null;
                        isShowView = false;
                    }
                }
            });
        }
    }

    public static void createFloatView(Context context, final View rootView, int x, int y, int width, int height, long duration, boolean focusable, int animRes) {
        int type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        createFloatView(context,rootView,x,y,width,height,duration,focusable,animRes,type);
    }

    public static void createFloatView(Context context, final View rootView, int x, int y, int width, int height, long duration, boolean focusable, int animRes,int WindowType) {
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wmParams.type = WindowType;
        if (focusable) {
            wmParams.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        } else {
            wmParams.flags = WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }
        wmParams.format = PixelFormat.RGBA_8888;

        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        wmParams.x = x;
        wmParams.y = y;

        wmParams.width = width;
        wmParams.height = height;

        if (animRes != -1)
            wmParams.windowAnimations = animRes;

        if (mWindowManager != null && rootView.getParent() == null) {
            mWindowManager.addView(rootView, wmParams);
        }

        rootView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        if (duration > 0) {
            if (mHandler == null) {
                mHandler = new Handler(Looper.getMainLooper());
            }
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    removeFloatView(rootView);
                }
            }, duration);

        }
    }


    public static void removeFloatView(View view) {
        Log.d(TAG, "removeFloatView: remove");
        if (view == null) {
            return;
        }
        try {
            mWindowManager.removeViewImmediate(view);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "removeFloatView: " + e.toString());
        }
    }

}
