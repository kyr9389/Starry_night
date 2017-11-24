package com.example.tcp.starry_night;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by tcp on 2017-09-07.
 */

public class MyListAdapter extends BaseAdapter {
    Context context;
    ArrayList<list_item> list_itemArrayList;
    ViewHolder viewholder;

    class ViewHolder {

        TextView nickname_textView;
        TextView title_textView;
        TextView date_textView;
        TextView content_textView;
        ImageView profile_imageView;

    }

    public MyListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list_itemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView==null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);

            viewholder = new ViewHolder();

            viewholder.nickname_textView = (TextView) convertView.findViewById(R.id.nickname_textview);
            viewholder.content_textView = (TextView) convertView.findViewById(R.id.content_textview);
            viewholder.title_textView = (TextView) convertView.findViewById(R.id.title_textview);
            viewholder.date_textView = (TextView) convertView.findViewById(R.id.date_textview);
            viewholder.profile_imageView = (ImageView) convertView.findViewById(R.id.profile_imageview);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder)convertView.getTag();
        }

        viewholder.nickname_textView.setText(list_itemArrayList.get(i).getNickname());
        viewholder.title_textView.setText(list_itemArrayList.get(i).getTitle());
        viewholder.content_textView.setText(list_itemArrayList.get(i).getContent());
        viewholder.date_textView.setText(list_itemArrayList.get(i).getWrite_date().toString());
        viewholder.profile_imageView.setImageResource(list_itemArrayList.get(i).getProfile_image());

        return convertView;
    }
}
