package com.example.firstcode.fifth_chapter.bestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

/**
 * 说明：$
 * <p>
 * date: 2019-12-02 22:23
 *
 * @author syd
 * @version 1.0
 */
public class OfflineBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("警告")
                .setCancelable(false)
                .setMessage("被迫下线提醒")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCollector.finishAll();
                        Intent intent1 = new Intent(context,LoginActivity.class);
                        context.startActivity(intent1);
                    }
                });
        builder.show();

    }
}
