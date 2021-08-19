package com.example.firstcode.third_chapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstCode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 说明：$
 * <p>
 * date: 2019-11-09 10:47
 *
 * @author syd
 * @version 1.0
 */
public class MsgActivity extends AppCompatActivity {

    List<Msg> list  =new ArrayList<>();
    EditText text ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_patch);
        initData();
        final RecyclerView recyclerView  = findViewById(R.id.rlv);
        final MsgAdapter msgAdapter = new MsgAdapter(list);
         text = findViewById(R.id.et_info);
        Button bt = findViewById(R.id.bt_send);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(msgAdapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                // 这里还是利用随机数来生成消息的类型
                int count = random.nextInt(20);
                Msg msg = new Msg(text.getText()+"count:"+count,count%2);
                list.add(msg);
                Msg msg1 = new Msg(text.getText()+"count1:"+count,count%2);
                list.add(msg1);
                // 表示在消息的末尾插入内容
                msgAdapter.notifyItemInserted(list.size()-2);
                // 让 RecyclerView 自动滚动到最底部
                recyclerView.scrollToPosition(list.size()-1);
                // 清空内容
                text.setText("");
            }
        });
    }

    public void initData(){
        Random random = new Random();
        for (int i=0;i<40;i++){
            int count = random.nextInt(20);
            Msg msg = new Msg("消息嗯哼"+i+"count:"+count,count%2);
            list.add(msg);
        }


    }
}
