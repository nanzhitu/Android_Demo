package com.example.contentresolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    private ListView listView;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) this.findViewById(R.id.listView);
        ContentResolver contentResolver = getContentResolver();
        Uri selectUri = Uri.parse("content://com.example.administrator.personProvider/person");
        cursor = contentResolver.query(selectUri,null,null,null,null);

        adapter = new SimpleCursorAdapter(this,R.layout.item,cursor,new String[]{
                "_id", "name", "age"}, new int[]{
                R.id.id, R.id.name, R.id.age
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView lView = (ListView)parent;
                Cursor data = (Cursor)lView.getItemAtPosition(position);
                int _id = data.getInt(data.getColumnIndex("_id  "));
                Toast.makeText(MainActivity.this, _id+"", Toast.LENGTH_LONG).show();
            }
        });
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver();
                Uri insertUri = Uri.parse("content://com.example.administrator.personProvider/person");
                ContentValues values = new ContentValues();
                values.put("name", "Neo");
                values.put("age", 24);
                Uri uri = contentResolver.insert(insertUri, values);
                Toast.makeText(MainActivity.this, "添加完成",Toast.LENGTH_LONG).show();
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentResolver contentResolver = getContentResolver();
                Uri queryUri = Uri.parse("content://com.example.administrator.personProvider/person");
                cursor = contentResolver.query(queryUri, null, null, null,null);
                adapter = new SimpleCursorAdapter(MainActivity.this,R.layout.item,cursor,new String[]{
                        "_id", "name", "age"}, new int[]{
                        R.id.id, R.id.name, R.id.age
                });
                listView.setAdapter(adapter);
            }
        });


    }
}
