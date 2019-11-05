package com.example.firstcode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstCode.R;
import com.example.firstcode.third_chapter.UIActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        initView();
    }

    public void initView(){
        Button bt_third_ui = findViewById(R.id.bt_third_chapter);
        bt_third_ui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 第三章基础 ui 控件
            case R.id.bt_third_chapter:
                UIActivity.actionStart(this);
                break;
        }
    }
}
