package com.example.firstcode.fifth_chapter.bestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2019-12-02 22:09
 *
 * @author syd
 * @version 1.0
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }

}
