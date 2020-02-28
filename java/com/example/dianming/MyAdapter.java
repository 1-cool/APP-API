package com.example.dianming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    //数据集合
    List<Map<String,Object>> list;
    //反射器
    LayoutInflater inflater;
    //构造器
    public MyAdapter(Context context) {
        inflater=LayoutInflater.from(context);
    }
    //传入数据
    public void setList(List<Map<String, Object>> list) {
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
        ViewHolder holder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item,null);
            holder=new ViewHolder();
            holder.pic=convertView.findViewById(R.id.intputpic);
            holder.name=convertView.findViewById(R.id.inputname);
            holder.num=convertView.findViewById(R.id.inputnum);
            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.pic.setImageResource((Integer) list.get(position).get("pic"));
        holder.name.setText((String) list.get(position).get("name"));
        holder.num.setText((String) list.get(position).get("num"));

      return convertView;
    }



    public class ViewHolder{
        ImageView pic;
        TextView name;
        TextView num;
    }
}
