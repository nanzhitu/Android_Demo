package com.example.contentproviderdemo;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    private ListView listView;
    private Cursor cursor;
    private PersonService personService;
    private  Button update;
    private  Button insert;
    private Button delete;
    private Button query;
    private EditText editText;

    private static int num = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        update = (Button)findViewById(R.id.button);
        insert = (Button)findViewById(R.id.button2);
        delete = (Button)findViewById(R.id.button3);
        query = (Button)findViewById(R.id.button4);
        editText = (EditText)findViewById(R.id.editText);
        listView = (ListView)findViewById(R.id.listView);

        personService = new PersonService(this);
        //List<Person> persons = personService.findAll();
        cursor = personService.findAll_cur();
        adapter = new SimpleCursorAdapter(this,R.layout.item,cursor,new String[]{
                "_id", "name", "age"}, new int[]{
                R.id.id, R.id.name, R.id.age});
        listView.setAdapter(adapter);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = editText.getText().toString();
                Person person = new Person(num+2,"aaa",age);

                personService.update(person);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person("bbb","15");
                personService.save(person);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personService.delete(num);
                num++;
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = personService.findAll_cur();
                adapter = new SimpleCursorAdapter(MainActivity.this,R.layout.item,cursor,new String[]{
                        "_id", "name", "age"}, new int[]{
                        R.id.id, R.id.name, R.id.age});
                listView.setAdapter(adapter);
            }
        });

    }
}
