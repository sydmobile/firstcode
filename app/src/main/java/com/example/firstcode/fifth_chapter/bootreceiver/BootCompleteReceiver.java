package com.example.firstcode.fifth_chapter.bootreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 说明：$
 * <p>
 * date: 2019-11-30 20:41
 *
 * @author syd
 * @version 1.0
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"开机完成",Toast.LENGTH_SHORT).show();
    }
}
