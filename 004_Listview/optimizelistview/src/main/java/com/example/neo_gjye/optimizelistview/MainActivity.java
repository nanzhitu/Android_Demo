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
        Student zhangsan1  = new Student("张三1", "30", "男", "喜欢玩游戏");
        Student lisi1  = new Student("李四1", "18", "女", "喜欢读书");
        Student wangwu1  = new Student("王五1", "25", "男", "喜欢运动");
        Student zhaoliu1  = new Student("赵六1", "22", "男", "喜欢吃饭");
        Student zhangsan2  = new Student("张三2", "30", "男", "喜欢玩游戏");
        Student lisi2  = new Student("李四2", "18", "女", "喜欢读书");
        Student wangwu2  = new Student("王五2", "25", "男", "喜欢运动");
        Student zhaoliu2  = new Student("赵六2", "22", "男", "喜欢吃饭");
        Student zhangsan3  = new Student("张三3", "30", "男", "喜欢玩游戏");
        Student lisi3  = new Student("李四3", "18", "女", "喜欢读书");
        Student wangwu3  = new Student("王五3", "25", "男", "喜欢运动");
        Student zhaoliu3  = new Student("赵六3", "22", "男", "喜欢吃饭");
        mData.add(zhangsan);
        mData.add(lisi);
        mData.add(wangwu);
        mData.add(zhaoliu);
        mData.add(zhangsan1);
        mData.add(lisi1);
        mData.add(wangwu1);
        mData.add(zhaoliu1);
        mData.add(zhangsan2);
        mData.add(lisi2);
        mData.add(wangwu2);
        mData.add(zhaoliu2);
        mData.add(zhangsan3);
        mData.add(lisi3);
        mData.add(wangwu3);
        mData.add(zhaoliu3);
    }
}
