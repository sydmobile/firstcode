package com.example.firstcode.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2020-07-20 21:10
 *
 * @author syd
 * @version 1.0
 */
public class FlowLayout extends ViewGroup {
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        List<View> listline = new ArrayList<>();
        int count = getChildCount();
        int width = 0;
        int height = 0;
        int widthUsed = 0;
        for (int i = 0;i<count;i++){
            View view
            measureChild(getChildAt(count),widthMeasureSpec,heightMeasureSpec);
            width = Math.max(width,getChildAt(i).getMeasuredWidth());
            widthUsed +=width+20;
            listline.add()
            if (widthUsed > MeasureSpec.getSize(widthMeasureSpec)){

            }
        }


    }
}
