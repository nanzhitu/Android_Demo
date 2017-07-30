package com.example.contentproviderdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import java.util.List;




/**
 * Created by Administrator on 2017/7/23.
 */

public class PersonService {

    private DBOpenHelper dbOpenHelper;

    public PersonService(Context context){

        dbOpenHelper = new DBOpenHelper(context);
    }

    public void save(Person person){

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("insert into person(name,age) values(?,?)",new Object[]{
                person.getName(), person.getAge()
        });
    }

    public void delete(Integer _id){

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from person where _id=?",new Object[]{_id});
    }

    public Person find(Integer _id){

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from person where _id=?", new String[]{_id.toString()});
        if(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            Person person = new Person();
            person.set_id(id);
            person.setAge(age);
            person.setName(name);
            return person;

        }
        return null;
    }

    public List<Person> findAll(){

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        List<Person> persons = new ArrayList<Person>();
        Cursor cursor = db.rawQuery("select * from person",null);
        while (cursor.moveToNext()){
            Person person = new Person();
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            person.set_id(id);
            person.setAge(age);
            person.setName(name);
            persons.add(person);
        }
        return persons;

    }

    public Cursor findAll_cur(){

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        //List<Person> persons = new ArrayList<Person>();
        Cursor cursor = db.rawQuery("select * from person",null);
        return cursor;
    }

    public Boolean update(Person person){

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("age", person.getAge());
        db.update("person", values, "_id=?", new String[]{person.get_id().toString()});
        db.close();
        return true;
    }
}
