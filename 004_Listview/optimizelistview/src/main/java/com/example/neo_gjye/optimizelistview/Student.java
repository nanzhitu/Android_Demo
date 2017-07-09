package com.example.neo_gjye.optimizelistview;

/**
 * Created by neo-gj.ye on 2017/5/19.
 */

public class Student {
    /*
    定义学生的构造器，创建学生对象时定义学生的信息。
     */
    public Student(String name, String age, String sex, String hobby){
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.hobby = hobby;

    }
    private String name;//学生姓名
    private String age;//学生年龄
    private String sex;//学生性别
    private String hobby;//学生爱好

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

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
