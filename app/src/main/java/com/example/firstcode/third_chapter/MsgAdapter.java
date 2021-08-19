package com.example.firstcode.third_chapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstCode.R;

import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2019-11-09 10:38
 *
 * @author syd
 * @version 1.0
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgViewHolder> {
    private List<Msg> list;
    public MsgAdapter(List<Msg> list){
        this.list = list;
    }



    class MsgViewHolder extends RecyclerView.ViewHolder{
        LinearLayout llLeft,llRight;
        TextView tvLeft,tvRight;

        public MsgViewHolder(@NonNull View itemView) {
            super(itemView);
            llLeft =itemView.findViewById(R.id.ll_left);
            llRight =itemView.findViewById(R.id.ll_right);
            tvLeft =itemView.findViewById(R.id.tv_left);
            tvRight =itemView.findViewById(R.id.tv_right);
        }
    }

    @NonNull
    @Override
    public MsgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        MsgViewHolder viewHolder = new MsgViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MsgViewHolder holder, int position) {
        Msg msg = list.get(position);
        // 这里根据消息的类型来选择不同的布局
        if (msg.getType() == Msg.TYPE_RECEIVE){
            holder.llLeft.setVisibility(View.VISIBLE);
            holder.llRight.setVisibility(View.GONE);
            holder.tvLeft.setText(msg.getContent());
        }else {
            holder.llRight.setVisibility(View.VISIBLE);
            holder.llLeft.setVisibility(View.GONE);
            holder.tvRight.setText(msg.getContent());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
