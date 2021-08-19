package com.example.firstcode.fourth_chapter.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.firstCode.R;

/**
 * 说明：$
 * <p>
 * date: 2019-11-27 20:48
 *
 * @author syd
 * @version 1.0
 */
public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        NesContentFragment fragment = (NesContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        fragment.refresh(title,content);
    }

    public static void actionStart(Context context,String title,String content){
        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }
}
