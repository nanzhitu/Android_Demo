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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    private ListView listView;
    private Cursor cursor;
    private static final String URL = "content://com.example.administrator.personProvider/";
    private static final String PERSON = "person";

    private static int num = 1;

    private  Button update;
    private  Button insert;
    private Button delete;
    private Button query;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        update = (Button)findViewById(R.id.button);
        insert = (Button)findViewById(R.id.button2);
        delete = (Button)findViewById(R.id.button3);
        query = (Button)findViewById(R.id.button4);
        editText = (EditText)findViewById(R.id.editText);
        listView=(ListView) this.findViewById(R.id.listView);
        ContentResolver contentResolver = getContentResolver();
        Uri selectUri = Uri.parse(URL+PERSON);
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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String age = editText.getText().toString();
                ContentResolver contentResolver = getContentResolver();
                Uri updateUri = Uri.parse(URL+PERSON+"/"+(num+1));
                ContentValues values = new ContentValues();
                values.put("age", age);
                contentResolver.update(updateUri,values,null,null);
                Toast.makeText(MainActivity.this, "修改完成",Toast.LENGTH_LONG).show();
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver();
                Uri insertUri = Uri.parse(URL+PERSON);
                ContentValues values = new ContentValues();
                values.put("name", "Neo");
                values.put("age", 24);
                Uri uri = contentResolver.insert(insertUri, values);
                Toast.makeText(MainActivity.this, "添加完成",Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver();
                Uri deleteUri = Uri.parse(URL+PERSON+"/"+num);
                contentResolver.delete(deleteUri,null,null);
                Toast.makeText(MainActivity.this, "删除完成",Toast.LENGTH_LONG).show();
                num++;
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentResolver contentResolver = getContentResolver();
                Uri queryUri = Uri.parse("content://com.example.administrator.personProvider/person");
                cursor = contentResolver.query(queryUri, null, null, null,null);
                cursor.getCount();
                if(cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    num = id;
                }
                adapter = new SimpleCursorAdapter(MainActivity.this,R.layout.item,cursor,new String[]{
                        "_id", "name", "age"}, new int[]{
                        R.id.id, R.id.name, R.id.age
                });
                listView.setAdapter(adapter);
            }
        });


    }
}
