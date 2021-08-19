package com.example.firstcode.eighth_chapter;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import com.example.firstCode.R;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("onReceive","===");
        Bundle bundle = RemoteInput.getResultsFromIntent(intent);
        if (bundle!=null){
            CharSequence charSequence = bundle.getCharSequence(NotificationActivity.key);
            Log.e("onReceive",charSequence.toString());
            Notification repliedNotification = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                repliedNotification = new Notification.Builder(context, "1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("已回复")
                        .build();
            }

            // Issue the new notification.
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(1, repliedNotification);
        }
    }
}
