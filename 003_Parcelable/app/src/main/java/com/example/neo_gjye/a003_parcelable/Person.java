package com.example.neo_gjye.a003_parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by neo-gj.ye on 2017/5/12.
 */

public class Person implements Parcelable {

    private static final  String TAG = ParcelableTest.TAG;
    private  String name;
    private int age;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    protected Person() {
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            Log.d(TAG, "createFromParcel");
            Person mPerson = new Person();
            mPerson.name = in.readString();
            mPerson.age = in.readInt();
            return mPerson;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        Log.d(TAG, "describeContents");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(TAG, "writeToParcel");
        dest.writeString(name);
        dest.writeInt(age);
    }
}
