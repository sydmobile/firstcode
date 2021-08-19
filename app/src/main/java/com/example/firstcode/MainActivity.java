package com.example.firstcode;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstCode.R;
import com.example.firstcode.eighth_chapter.MediaActivity;
import com.example.firstcode.eighth_chapter.NotificationActivity;
import com.example.firstcode.fifth_chapter.NetChangeActivity;
import com.example.firstcode.fifth_chapter.bestpractice.LoginActivity;
import com.example.firstcode.fourth_chapter.FragmentBaseActivity;
import com.example.firstcode.fourth_chapter.news.ActivityNews;
import com.example.firstcode.ninth.HttpRequestActivity;
import com.example.firstcode.ninth.WebViewActivity;
import com.example.firstcode.ninth.XmlParseActivity;
import com.example.firstcode.seventh_chapter.contacts.ContactsActivity;
import com.example.firstcode.seventh_chapter.permission.PermissionActivity;
import com.example.firstcode.sixth_chapter.FileSaveActivity;
import com.example.firstcode.sixth_chapter.db.DBDemoActivity;
import com.example.firstcode.test.TestActivity;
import com.example.firstcode.third_chapter.UIActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        initView();
    }

    public void initView() {
        Button bt_third_ui = findViewById(R.id.bt_third_chapter);
        bt_third_ui.setOnClickListener(this);
        Button btFragment = findViewById(R.id.bt_fragment);
        btFragment.setOnClickListener(this);
        Button btNews = findViewById(R.id.bt_news);
        btNews.setOnClickListener(this);
        Button btNetChange = findViewById(R.id.bt_net_change);
        btNetChange.setOnClickListener(this);
        Button btOffline = findViewById(R.id.bt_offline);
        btOffline.setOnClickListener(this);
        Button btSave = findViewById(R.id.bt_save);
        btSave.setOnClickListener(this);
        Button btDb = findViewById(R.id.bt_db);
        btDb.setOnClickListener(this);
        Button btPermission = findViewById(R.id.bt_permission);
        btPermission.setOnClickListener(this);
        Button btReadContracts = findViewById(R.id.bt_read_contracts);
        btReadContracts.setOnClickListener(this);
        Button btNotification = findViewById(R.id.bt_notification);
        btNotification.setOnClickListener(this);
        Button btTakePhoto = findViewById(R.id.bt_take_photo);
        btTakePhoto.setOnClickListener(this);
        Button btWebView = findViewById(R.id.bt_wb);
        btWebView.setOnClickListener(this);
        Button btXmlParse = findViewById(R.id.bt_xml_pares);
        btXmlParse.setOnClickListener(this);
        Button btHttRequest = findViewById(R.id.bt_http_request);
        btHttRequest.setOnClickListener(this);
        Button bt = findViewById(R.id.bt);
        bt.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 第三章基础 ui 控件
            case R.id.bt_third_chapter:
                UIActivity.actionStart(this);
                break;
            case R.id.bt_fragment:
                Intent intent = new Intent(this, FragmentBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_news:
                Intent intent1 = new Intent(this, ActivityNews.class);
                startActivity(intent1);
                break;
            case R.id.bt_net_change:
                Intent intent2 = new Intent(this, NetChangeActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_offline:
                Intent intent3 = new Intent(this, LoginActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt_save:
                Intent intent4 = new Intent(this, FileSaveActivity.class);
                startActivity(intent4);
                break;
            case R.id.bt_db:
                Intent intent5 = new Intent();
                intent5.setComponent(new ComponentName(this, DBDemoActivity.class));
                startActivity(intent5);
                break;
            case R.id.bt_permission:
                Intent intent6 = new Intent();
                intent6.setComponent(new ComponentName(this, PermissionActivity.class));
                startActivity(intent6);
                break;
            case R.id.bt_read_contracts:
                Intent intent7 = new Intent(this, ContactsActivity.class);
                startActivity(intent7);
                break;
            case R.id.bt_notification:
                Intent intent8 = new Intent(this, NotificationActivity.class);
                startActivity(intent8);
                break;
            case R.id.bt_take_photo:
                Intent intent9 = new Intent(this, MediaActivity.class);
                startActivity(intent9);
                break;
            case R.id.bt_wb:
                Intent intent10 = new Intent(this, WebViewActivity.class);
                startActivity(intent10);
                break;
            case R.id.bt_xml_pares:
                Intent intent11 = new Intent(this, XmlParseActivity.class);
                startActivity(intent11);
                break;
            case R.id.bt_http_request:
                Intent intent12 = new Intent(this, HttpRequestActivity.class);
                startActivity(intent12);
                break;
            case R.id.bt:
                Intent intent13 = new Intent(this, TestActivity.class);
                startActivity(intent13);
                new Thread(()->{

                }).start();
                break;
        }
    }
}
