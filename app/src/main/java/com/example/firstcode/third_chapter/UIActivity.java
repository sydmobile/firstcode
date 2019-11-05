package com.example.firstcode.third_chapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.percentlayout.widget.PercentRelativeLayout;

import com.example.firstCode.R;

/**
 * 说明：第三章UI
 * <p>
 * date: 2019-10-28 22:30
 *
 * @author syd
 * @version 1.0
 */
public class UIActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.hide();
        }
        initView();

    }

    /**
     * 初始化控件
     */
    public void initView() {
        Button btAlertDialog = findViewById(R.id.bt_alert_dialog);
        btAlertDialog.setOnClickListener(this);
        Button btProgressDialog = findViewById(R.id.bt_progress_dialog);
        btProgressDialog.setOnClickListener(this);
        Button btListView = findViewById(R.id.bt_listview);
        btListView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // AlertDialog 控件学习
            case R.id.bt_alert_dialog:
//                alertDialog();
                FrameLayoutActivity.acitonStart(this);
                break;
            // ProgressDialog 学习
            case R.id.bt_progress_dialog:
                progressDialog();
                break;
            case R.id.bt_listview:
                ListIViewActivity.actionStart(this);
                break;

        }
    }


    /**
     * AlertDialog 学习
     */
    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("题目");
        builder.setMessage("内容消息");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击后弹出框自动消失
                // which 代表点击的是哪一个 button 对应下面的值
                /** The identifier for the positive button. */
                int BUTTON_POSITIVE = -1;

                /** The identifier for the negative button. */
                int BUTTON_NEGATIVE = -2;

                /** The identifier for the neutral button. */
                int BUTTON_NEUTRAL = -3;

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击后弹出框自动消失
            }
        });
        builder.show();
    }

    public void progressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在加载");
        progressDialog.setMessage("请稍等......");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    /**
     * 启动
     *
     * @param context 上下文
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UIActivity.class);
        context.startActivity(intent);

    }


}

