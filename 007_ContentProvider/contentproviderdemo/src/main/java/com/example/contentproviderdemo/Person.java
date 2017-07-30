package com.example.contentproviderdemo;

/**
 * Created by Administrator on 2017/7/23.
 */

public class Person {
    private Integer _id;
    private String name;
    private String age;

    public Person(){}

    public Person( String name, String age)
    {
        this._id = 1;
        this.name = name;
        this.age = age;
    }
    public Person(Integer _id, String name, String age)
    {
        this._id = _id;
        this.name = name;
        this.age = age;
    }
    public Integer get_id() {
        return _id;
    }
    public void set_id(Integer _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
