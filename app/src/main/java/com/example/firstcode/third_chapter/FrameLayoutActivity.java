package com.example.firstcode.third_chapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstCode.R;

/**
 * 说明：$
 * <p>
 * date: 2019-11-03 22:49
 *
 * @author syd
 * @version 1.0
 */
public class FrameLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
    }


    public static void acitonStart(Context context){
        Intent intent = new Intent(context,FrameLayoutActivity.class);
        context.startActivity(intent);
    }
}
