package com.example.firstcode.third_chapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstCode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2019-11-04 22:49
 *
 * @author syd
 * @version 1.0
 */
public class ListIViewActivity extends AppCompatActivity {
    private String[] data = {
            "Apple","fdsfs","fdsfssfs","wwww","wwwqqq","qqqqq","eeeee","rrrrrr","tttttt","yyyyyy","uuuuuu","aaaaaa","sssss"
    };
    List<Fruit> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviwe);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        initData();
        FruitAdapter fruitAdapter = new FruitAdapter(this,R.layout.fuit_item,list);
        ListView listView = findViewById(R.id.lv);
//        listView.setAdapter(adapter);
        listView.setAdapter(fruitAdapter);
        Log.e("List","==");
        Log.e("ss",listView.toString());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void initData(){
        list  = new ArrayList<>();
        for (int i=0;i<30;i++){
            Fruit fruit = new Fruit("水果"+i,R.mipmap.ic_launcher);
            list.add(fruit);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Activity();
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context,ListIViewActivity.class);
        context.startActivity(intent);
    }
}
