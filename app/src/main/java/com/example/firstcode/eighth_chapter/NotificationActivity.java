package com.example.firstcode.eighth_chapter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import com.example.firstCode.R;
import com.example.firstcode.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 说明：Notification 练习
 * <p>
 * date: 2020-03-03 08:42
 *
 * @author syd
 * @version 1.0
 */
public class NotificationActivity extends AppCompatActivity {
    public static String key = "key_text_reply";
    // 进度条通知
    int a= 10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        // 简单的创建一般通知
        Button btCreate = findViewById(R.id.bt_one);
        btCreate.setText("创建通知");
        IntentFilter intentFilter = new IntentFilter("com.xx");
        MyReceiver myReceiver = new MyReceiver();
        registerReceiver(myReceiver, intentFilter);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // "name" 用于在手机上显示
            NotificationChannel notificationChannel = new NotificationChannel("1", "name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);

            btCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this
                            , 0, intent, 0);
                    Notification notification = new NotificationCompat.Builder(NotificationActivity.this, "1")
                            .setContentTitle("Title")
                            .setContentText("This is content text")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            // 是按照比例来的，大小已经限定了,部分手机是如果没有设置。就使用小图标的
//                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .build();
                    notificationManager.notify(2, notification);

                }
            });
        }

        // 带有直接回复操作的通知
        // 回复框
        RemoteInput remoteInput = new RemoteInput.Builder(key)
                .setLabel("label")
                .build();
        Intent intent = new Intent(this, MyReceiver.class);
        intent.setAction("com.xx");
        PendingIntent replyPendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        final NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background, "title", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();
        Button btReplyNotification = findViewById(R.id.bt_two);
        btReplyNotification.setText("通知——直接回复");
        btReplyNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = new NotificationCompat.Builder(NotificationActivity.this, "1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("直接回复")
                        .setContentText("请回复")
                        // 用于添加一个按钮
                        .addAction(action)
                        .addAction(action)
                        .build();
                NotificationManagerCompat.from(NotificationActivity.this).notify(1, notification);
            }
        });


        Button btProgressNotification = findViewById(R.id.bt_three);
        btProgressNotification.setText("进度条通知");
        btProgressNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this, "1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("正在下载")
                        .setContentTitle("Title")
                        .setProgress(100,0,false);
                NotificationManagerCompat.from(NotificationActivity.this).notify(1, builder.build());

                final Timer timer = new Timer();
                a = 10;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        a+=10;
                        NotificationManagerCompat.from(NotificationActivity.this).notify(1,   builder.setProgress(100,a,false).build());
                        if (a == 100){
                            builder.setContentText("下载完成");
                            NotificationManagerCompat.from(NotificationActivity.this).notify(1,   builder.setProgress(0,0,false).build());
                            timer.cancel();
                        }
                    }
                },1,500);
            }
        });

        // 紧急通知
        Button btUrgent = findViewById(R.id.bt_four);
        btUrgent.setText("紧急通知");
        Intent intent1 = new Intent(this,MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent1,0);
        btUrgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = new NotificationCompat.Builder(NotificationActivity.this,"1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("紧急通知")
                        .setContentText("。。。。。。。。。。。。")
                        .setFullScreenIntent(pendingIntent,true)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .build();
                NotificationManagerCompat.from(NotificationActivity.this).notify(4,notification);
            }
        });

        Button btBigPicture = findViewById(R.id.bt_five);
        btBigPicture.setText("大图显示");
        btBigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = new NotificationCompat.Builder(NotificationActivity.this,"1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("图片")
                        .setContentText("这是一张图片")
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.weixin)))
                        .build();
                NotificationManagerCompat.from(NotificationActivity.this).notify(5,notification);
            }
        });

    }

    public static void get() {

    }
}
