package com.example.neo_gjye.optimizelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Student> mData;
    private ListView mListViewArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewArray = (ListView)findViewById(R.id.listview);
        LayoutInflater inflater = getLayoutInflater();
        initData();
        StudentAdapter adapter = new StudentAdapter(inflater,mData);
        mListViewArray.setAdapter(adapter);

    }
    /*
   初始化数据
    */
    private void initData() {
        mData = new ArrayList<Student>();
        Student zhangsan  = new Student("张三", "30", "男", "喜欢玩游戏");
        Student lisi  = new Student("李四", "18", "女", "喜欢读书");
        Student wangwu  = new Student("王五", "25", "男", "喜欢运动");
        Student zhaoliu  = new Student("赵六", "22", "男", "喜欢吃饭");
        mData.add(zhangsan);
        mData.add(lisi);
        mData.add(wangwu);
        mData.add(zhaoliu);
    }
}
