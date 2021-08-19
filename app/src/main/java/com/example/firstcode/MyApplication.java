package com.example.firstcode;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.firstCode.R;

import org.litepal.LitePal;

/**
 * 说明：$
 * <p>
 * date: 2019-10-19 19:49
 *
 * @author syd
 * @version 1.0
 */
public class MyApplication extends Application {
    public static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        LitePal.initialize(this);
        Log.e("myapplication","oncreate()");
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
    public  static void loadImage(String uil, ImageView imgView){
        Glide.with(getInstance()).load(uil).
                placeholder(R.mipmap.ic_launcher).
                error(R.drawable.ic_launcher_background).override(480,2088).fitCenter().
                into(imgView);
    }
}
