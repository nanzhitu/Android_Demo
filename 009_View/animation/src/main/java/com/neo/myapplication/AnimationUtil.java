package com.neo.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by Neo on 2018/10/31.
 */

public class AnimationUtil {

    private static final String TAG = "AnimationUtil";
    private static Singleton<AnimationUtil> sSingleton;

    private AnimationUtil() {
    }

    public static AnimationUtil getInstance(){
        if(sSingleton == null){
            sSingleton = new Singleton<AnimationUtil>() {
                @Override
                protected AnimationUtil createInstance() {
                    return new AnimationUtil();
                }
            };
        }
        return sSingleton.getInstance();
    }


    public void translateAnimation(final View view){


        final float fromX = 0.0f,toX = 300.0f,fromY = 0.0f,toY = 300.0f;
        TranslateAnimation animation = new TranslateAnimation(fromX, toX, fromY, toY);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ConstraintLayout.LayoutParams ll = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                ll.setMargins((int)(toX - fromX)*2, (int)(toY - fromY)*2, 0, 0);
                view.setLayoutParams(ll);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setDuration(3000);
        view.startAnimation(animation);
    }



    public void animationSet(View view){
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(view, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();
    }


    public void translateUpDown(final View view, int start, int end, final OnAnimatorListener listener){
        final RelativeLayout.LayoutParams ll_newmsg = (RelativeLayout.LayoutParams) view.getLayoutParams();

        ValueAnimator animator = ObjectAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                //FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)capture_relative.getLayoutParams();
                ll_newmsg.topMargin = value;
                view.setLayoutParams(ll_newmsg);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (listener != null) {
                    listener.onAnimationEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

    public interface OnAnimatorListener {
        /**
         * 动画完成后触发回调
         */
        void onAnimationEnd( );
    }
}
