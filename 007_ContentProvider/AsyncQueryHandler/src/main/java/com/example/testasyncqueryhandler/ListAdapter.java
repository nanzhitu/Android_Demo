package com.example.testasyncqueryhandler;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */

public class ListAdapter  extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ContentValues> list;
    public static final String NAME = "name", NUMBER = "number", SORT_KEY = "sort_key";

    public ListAdapter(Context context, List<ContentValues> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContentValues cv = list.get(position);
        holder.name.setText(cv.getAsString(NAME));
        holder.number.setText(cv.getAsString(NUMBER));

        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView number;
    }
}
