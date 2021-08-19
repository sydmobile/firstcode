package com.example.firstcode.fifth_chapter.bestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.firstCode.R;

/**
 * 说明：$
 * <p>
 * date: 2019-12-02 22:19
 *
 * @author syd
 * @version 1.0
 */
public class SuccessActivity extends  BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        Button btSend = findViewById(R.id.bt_send);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.syd.offline");
                sendBroadcast(intent);
            }
        });
    }
}
