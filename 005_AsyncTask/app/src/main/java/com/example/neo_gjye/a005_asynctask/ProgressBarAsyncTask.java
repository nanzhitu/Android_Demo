package com.example.neo_gjye.a005_asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by neo-gj.ye on 2017/6/9.
 */


/**
 * 生成该类的对象，并调用execute方法之后
 * 首先执行的是onProExecute方法
 * 其次执行doInBackgroup方法
 *
 */
public class ProgressBarAsyncTask extends AsyncTask<Integer, Integer, String> {

    public static final String TAG = "AsyncTask";
    private TextView textView;
    private ProgressBar progressBar;

    public ProgressBarAsyncTask(TextView textView, ProgressBar progressBar){
        super();
        this.textView = textView;
        this.progressBar = progressBar;
    }

    /**
     * 这里的Integer参数对应AsyncTask中的第一个参数
     * 这里的String返回值对应AsyncTask的第三个参数
     * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
     * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
     */
    @Override
    protected String doInBackground(Integer... params) {
        NetOperator netOperator = new NetOperator();
        int i = 0;
        for(i = 10; i <= 100; i +=10){
            netOperator.operator();
            publishProgress(i);
            Log.d(TAG, "publishProgress= %d"+i);
        }
        return i + params[0].intValue() + "" ;
    }

    /**
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
     */

    @Override
    protected void onPostExecute(String s) {
        textView.setText("异步操作执行结束" + s);
    }

    //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置

    @Override
    protected void onPreExecute() {
        textView.setText("开始执行异步线程");
    }

    /**
     * 这里的Intege参数对应AsyncTask中的第二个参数
     * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
     * onProgressUpdate是在UI线程中执行，所有可以对UI空间进行操作
     */

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        Log.d(TAG, "onProgressUpdate= "+value);
        progressBar.setProgress(value);
    }
}
