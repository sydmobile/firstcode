package com.example.firstcode.fifth_chapter.norboardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("onreceive","====");
//        Toast.makeText(context,"接收到了",Toast.LENGTH_SHORT).show();
    }
}
