package com.example.firstcode.third_chapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.firstCode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 说明：RecyclerView Activity
 * <p>
 * date: 2019-11-08 21:58
 *
 * @author syd
 * @version 1.0
 */
public class RecyclerViewActivity extends AppCompatActivity {

    List<Fruit> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化数据源
        initData();
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.rlv);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        FruitAdapter2 fruitAdapter2 = new FruitAdapter2(list);
        recyclerView.setAdapter(fruitAdapter2);

    }


    public void initData(){
        list  = new ArrayList<>();
        Random random = new Random();
        for (int i=0;i<30;i++){
            int length =random.nextInt(20)+5;
            StringBuilder stringBuilder  = new StringBuilder();
            for (int j =0;j<length;j++){
                stringBuilder.append("水果").append(i).append("++").append(length);
            }
            Fruit fruit = new Fruit(stringBuilder.toString(),R.mipmap.ic_launcher);
            list.add(fruit);
        }
    }

}
