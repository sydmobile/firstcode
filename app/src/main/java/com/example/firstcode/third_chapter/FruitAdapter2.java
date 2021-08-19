package com.example.firstcode.third_chapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstCode.R;

import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2019-11-06 23:20
 *
 * @author syd
 * @version 1.0
 */
public class FruitAdapter2 extends RecyclerView.Adapter<FruitAdapter2.ViewHolder> {
    private List<Fruit> listFruit;

    public FruitAdapter2(List<Fruit> listFruit){
        this.listFruit = listFruit;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView iv;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv_name);
            view = itemView;
        }
    }

    @NonNull
    @Override
    public FruitAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fuit_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = listFruit.get(position);
                Toast.makeText(v.getContext(),fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"img",Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter2.ViewHolder holder, int position) {
        holder.iv.setImageResource(listFruit.get(position).getImgId());
        holder.tv.setText(listFruit.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return listFruit.size();
    }
}
