package com.example.firstcode.third_chapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.firstCode.R;

import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2019-11-06 21:51
 *
 * @author syd
 * @version 1.0
 */
public class FruitAdapter  extends ArrayAdapter<Fruit> {

    private int resourceId;
    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
        resourceId = resource;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 获取当前项的 Fruit 实例
        Log.e("ADApter",parent.toString());
        Fruit fruit = getItem(position);
        View view ;
        ViewHolder viewHolder;
        if (convertView == null){

            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder  = new ViewHolder();
            viewHolder.iv =  view.findViewById(R.id.iv);
            viewHolder.tv = view.findViewById(R.id.tv_name);
            view.setTag(viewHolder); // 将 ViewHolder 存储在 View 中
        }else {

            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.iv.setImageResource(R.mipmap.ic_launcher);
        viewHolder.tv.setText(fruit.getName());
        return view;


    }
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
