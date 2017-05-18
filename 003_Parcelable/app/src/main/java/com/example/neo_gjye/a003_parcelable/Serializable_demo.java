package com.example.neo_gjye.a003_parcelable;

import java.io.Serializable;


/**
 * Created by neo-gj.ye on 2017/5/12.
 */

public class Serializable_demo implements Serializable{
        private int sno;
        private String sname;

        public Serializable_demo(){

        }
        public Serializable_demo(int sno, String sname){
            this.sname = sname;
            this.sno = sno;
        }

        public int getSno(){
            return sno;
        }

        public void setSno(int sno){
            this.sno = sno;
        }

        public String getSname(){
            return sname;
        }

        public void setSname(String sname){
            this.sname = sname;
        }

        @Override
        public String toString() {
            return "sno：" + sno + " name: " + sname;
        }
}


