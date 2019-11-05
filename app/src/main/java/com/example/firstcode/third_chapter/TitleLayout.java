package com.example.firstcode.third_chapter;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.firstCode.R;

/**
 * 说明：$
 * <p>
 * date: 2019-11-04 22:40
 *
 * @author syd
 * @version 1.0
 */
public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button btBack = findViewById(R.id.title_back);
        Button btEdit = findViewById(R.id.bt_edit);
        btBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
        btEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 你自己想做的事情
            }
        });
    }
}
