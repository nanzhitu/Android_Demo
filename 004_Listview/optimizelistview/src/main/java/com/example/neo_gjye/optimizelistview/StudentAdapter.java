package com.example.neo_gjye.optimizelistview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by neo-gj.ye on 2017/5/19.
 */

public class StudentAdapter extends BaseAdapter {

    private List<Student> mData;
    private LayoutInflater mInflater;

    public StudentAdapter(LayoutInflater inflater, List<Student> data){
        mInflater = inflater;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        Student student = mData.get(position);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_simpleadapter,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);
            viewHolder.age = (TextView)convertView.findViewById(R.id.age);
            viewHolder.sex = (TextView)convertView.findViewById(R.id.sex);
            viewHolder.hobby = (TextView)convertView.findViewById(R.id.hobby);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(student.getName());
        viewHolder.age.setText(student.getAge());
        viewHolder.sex.setText(student.getSex());
        viewHolder.hobby.setText(student.getHobby());
        return convertView;
    }

    class ViewHolder{
        TextView name;
        TextView age;
        TextView sex;
        TextView hobby;
    }
}
