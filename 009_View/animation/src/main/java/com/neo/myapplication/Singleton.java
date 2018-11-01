package com.neo.myapplication;

/**
 * Created by Neo on 2018/10/31.
 */

public abstract class Singleton<T> {
    private volatile T mInstance;

    protected abstract T createInstance();

    public final T getInstance() {
        if (mInstance == null) {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = createInstance();
                }
            }
        }
        return mInstance;
    }
}
