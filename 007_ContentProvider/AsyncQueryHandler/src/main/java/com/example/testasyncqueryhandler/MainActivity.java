package com.example.testasyncqueryhandler;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.testasyncqueryhandler.ListAdapter.NAME;
import static com.example.testasyncqueryhandler.ListAdapter.NUMBER;
import static com.example.testasyncqueryhandler.ListAdapter.SORT_KEY;

public class MainActivity extends AppCompatActivity {



    private List<ContentValues> listData;
    private AsyncQueryHandler asyncQuery;

    private ListView personList;
    private BaseAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personList = (ListView) findViewById(R.id.list_view);
        asyncQuery = new MyAsyncQueryHandler(getContentResolver());
        //异步读取联系人的信息
        asyncQueryContact();
    }

    private void asyncQueryContact() {
        // TODO Auto-generated method stub
        Uri uri = Uri.parse("content://com.android.contacts/data/phones");
        String[] projection = { "_id", "display_name", "data1", "sort_key" };
        asyncQuery.startQuery(0, null, uri, projection, null, null,"sort_key COLLATE LOCALIZED asc");
    }


    private class MyAsyncQueryHandler extends AsyncQueryHandler {

        public MyAsyncQueryHandler(ContentResolver cr) {
            super(cr);

        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                listData = new ArrayList<ContentValues>();
                //cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    ContentValues cv = new ContentValues();
                    cursor.moveToPosition(i);
                    String name = cursor.getString(1);
                    String number = cursor.getString(2);
                    String sortKey = cursor.getString(3);
                    if (number.startsWith("+86")) {
                        cv.put(NAME, name);
                        //process (+86)
                        cv.put(NUMBER, number.substring(3));
                        cv.put(SORT_KEY, sortKey);
                    } else {
                        cv.put(NAME, name);
                        cv.put(NUMBER, number);
                        cv.put(SORT_KEY, sortKey);
                    }
                    listData.add(cv);
                }
                if (listData.size() > 0) {
                    setAdapter(listData);
                }
                cursor.close();
            }
        }

    }

    private void setAdapter(List<ContentValues> listData) {
        adapter = new ListAdapter(this, listData);
        personList.setAdapter(adapter);

    }

}
