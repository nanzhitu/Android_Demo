package com.neo.gridview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 所有的图片
     */
    private List<String> mImgs;

    private GridView mGirdView;
    private ListAdapter mAdapter;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();

    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            mProgressDialog.dismiss();
            mImgs = Arrays.asList(mImgDir.list(new FilenameFilter()
            {
                @Override
                public boolean accept(File dir, String filename)
                {
                    if (filename.endsWith(".jpg"))
                        return true;
                    return false;
                }
            }));
            for(String i : mImgs){
                Log.d("ygj","mImgs = "+i);
            }
            /**
             * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
             */
            mAdapter = new MyAdapter(getApplicationContext(), mImgs,
                    mImgDir.getAbsolutePath());
            mGirdView.setAdapter(mAdapter);
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGirdView = (GridView) findViewById(R.id.id_gridView);

//        String[] permission = {"android.permission.READ_EXTERNAL_STORAGE"};
//		needPermission(this, 1, permission);
//		Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//		intent.setData(Uri.parse("package:" + getPackageName()));
//		startActivityForResult(intent, 100);

        getImages();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100){
            Log.d("Neo","onActivityResult resultCode = "+resultCode+" , data = "+data);
        }
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages()
    {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = MainActivity.this
                        .getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[] { "image/jpeg", "image/png" },
                        MediaStore.Images.Media.DATE_MODIFIED);

                while (mCursor.moveToNext())
                {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    String dirPath = parentFile.getAbsolutePath();

                    //利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if(mDirPaths.contains(dirPath))
                    {
                        continue;
                    }
                    else
                    {
                        mDirPaths.add(dirPath);
                    }

                    int picSize = parentFile.list(new FilenameFilter()
                    {
                        @Override
                        public boolean accept(File dir, String filename)
                        {
                            if (filename.endsWith(".jpg"))
                                return true;
                            return false;
                        }
                    }).length;
                    if (picSize > mPicsSize)
                    {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();
                //扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null ;
                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);

            }
        }).start();

    }


    public static void needPermission(Activity activity, int requestCode, String[] permissions){
        List<String> denyPermissions = findDeniedPermissions(activity,permissions);
        if(denyPermissions.size() > 0){
            requestPermissions(activity,denyPermissions.toArray(new String[denyPermissions.size()]),requestCode);
        }
    }

    public static void requestPermissions(Activity activity, String[] permission, int requestcode){
        ActivityCompat.requestPermissions(activity,permission, requestcode);
    }

    public static List<String> findDeniedPermissions(Activity activity, String... permission)
    {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission)
        {
            if (ContextCompat.checkSelfPermission(activity,value) != PackageManager.PERMISSION_GRANTED)
            {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }
}
