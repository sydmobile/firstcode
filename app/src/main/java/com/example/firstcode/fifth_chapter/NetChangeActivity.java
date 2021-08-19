package com.example.firstcode.fifth_chapter;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstCode.R;
import com.example.firstcode.fifth_chapter.norboardcast.MyReceiver;

/**
 * 说明：$
 * <p>
 * date: 2019-11-28 21:51
 *
 * @author syd
 * @version 1.0
 */
public class NetChangeActivity extends AppCompatActivity {
    NetWorkChangeReceiver netWorkChangeReceiver;
    Button buttonSend;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeReceiver = new NetWorkChangeReceiver();
        registerReceiver(netWorkChangeReceiver,intentFilter);
        buttonSend = findViewById(R.id.bt_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.my_broadcast.MY_BROADCAST");
                intent.setComponent(new ComponentName(NetChangeActivity.this, MyReceiver.class));
                NetChangeActivity.this.sendBroadcast(intent);
                Log.e("==","onckic");


            }
        });
    }

    class NetWorkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null && networkInfo.isAvailable()){
                Toast.makeText(context,"网络可用",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"网络不可用",Toast.LENGTH_SHORT).show();
            }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangeReceiver);
    }
}
