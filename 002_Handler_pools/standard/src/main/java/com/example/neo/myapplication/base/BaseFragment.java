package com.example.neo.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neo.myapplication.utils.LogUtils;

import java.lang.ref.WeakReference;



/**
 * Created by Mocc on 2018/1/3
 */

public abstract class BaseFragment extends Fragment {

    protected String TAG = getClass().getName();
    protected MyHandler mHandler;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.d(TAG, "setUserVisibleHint  " + isVisibleToUser);
        if (mHandler == null)
            mHandler = new MyHandler(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mHandler == null)
            mHandler = new MyHandler(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        LogUtils.e(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.e(TAG, "onCreateView");
        View view = initView(inflater);
        initListener();
        return view;
    }

    protected abstract View initView(LayoutInflater inflater);

    protected abstract void initListener();

    protected abstract void init();

    protected static class MyHandler extends Handler {

        WeakReference<BaseFragment> weakReference;

        public MyHandler(BaseFragment fragment) {
            this.weakReference = new WeakReference<BaseFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseFragment fragment = weakReference.get();
            if (fragment != null) {
                fragment.handleMessage(msg);
            }
        }
    }

    protected void handleMessage(Message msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.e(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG, "onDestroy");
        if (mHandler != null) {
            mHandler.weakReference.clear();
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}