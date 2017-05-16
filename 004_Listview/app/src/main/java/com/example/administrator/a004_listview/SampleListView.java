package com.example.administrator.a004_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SampleListView extends AppCompatActivity {

    private ListView listView;
    private String[] str_name = new String[] { "jack", "debb", "robin", "kikt", "dog", "cat", "elep" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list_view);
        listView = (ListView)findViewById(R.id.listview);

        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<30;i++)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "This is Title.....");
            map.put("ItemText", "This is text.....");
            mylist.add(map);
        }
        SimpleAdapter mSchedule = new SimpleAdapter(this,mylist,R.layout.listview,new String[] {"ItemTitle", "ItemText"},new int[] {R.id.str_name,R.id.ItemText});
        listView.setAdapter(mSchedule);

    }


}
