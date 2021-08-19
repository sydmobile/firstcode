package com.example.firstcode.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

import com.example.firstCode.R;

/**
 * @author：动脑学院-Zee老师
 * @date：2020/6/9 20:08
 * @email：575569745@qq.com
 * @description: 1、从raw获取china.svg文件
 * 2.解析svg，将每一个path节点转变成Android当中的path对象
 * 3.通过canvas和paint将每一个path对象绘制出来
 */
public class ChinaMapView extends View {
    private Context context;
    //存放所有省份的容器
    private List<Province> provinceList = new ArrayList<>();
    private Paint paint;
    //是否解析完
    private boolean isFinishedParse = false;
    //地图的区域
    private RectF totalRectF;
    //地图缩放比
    private float scale = 1f;

    public ChinaMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ChinaMapView(Context context) {
        super(context);
    }

    public ChinaMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ChinaMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 拿到china.svg，对文件进行解析
     * 初始化画笔
     */
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //解析文件属于耗时操作，应该放到工作线程当中来做
        thread.start();
//        parse();
    }

    private Thread thread = new Thread() {
        @Override
        public void run() {
            //获取china.svg文件
            parse();
            //解析完毕，通知刷新重新执行onMeasure(),onDraw()进行绘制
            handler.sendEmptyMessage(1);
        }
    };

    private void parse() {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.china);
            //将InputStream转化为对象，实际上就是对xml数据进行解析/DOM/SAX,Glide sqlite okhttp
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            //获取根节点
            Element rootElement = document.getDocumentElement();
            //获取根节点下面的所有path节点
            NodeList nodeList = rootElement.getElementsByTagName("path");
            //遍历所有的path节点，将节点转化为path对象
            int length = nodeList.getLength();
            Province province;
            float left = -1;
            float top = -1;
            float right = -1;
            float bottom = -1;
            for (int i = 0; i < length; i++) {
                Element element = (Element) nodeList.item(i);
                province = new Province();
                //获取节点的所有属性
                String fillColor = element.getAttribute("android:fillColor");
                String strokeColor = element.getAttribute("android:strokeColor");
                String strokeWidth = element.getAttribute("android:strokeWidth");
                String pathData = element.getAttribute("android:pathData");
                province.fillColor = Color.parseColor(fillColor);
                province.strokeColor = Color.parseColor(strokeColor);
                province.strokeWidth = Float.parseFloat(strokeWidth);
                Path path = PathParser.createPathFromPathData(pathData);
                province.path = path;
                //计算地图的区域
                RectF rectF = new RectF();
                path.computeBounds(rectF, true);
                province.rectF = rectF;
                left = left == -1 ? rectF.left : Math.min(left, rectF.left);
                top = top == -1 ? rectF.top : Math.min(top, rectF.top);
                right = right == -1 ? rectF.right : Math.max(right, rectF.right);
                bottom = bottom == -1 ? rectF.bottom : Math.max(bottom, rectF.bottom);
                provinceList.add(province);
            }
            totalRectF = new RectF(left, top, right, bottom);
            isFinishedParse = true;
        } catch (Exception e) {
            Log.d("ChinaMapView", e.getMessage());
            e.printStackTrace();
        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    requestLayout();
//                    postInvalidate();
                    break;
            }
        }
    };

    /**
     * 1测量当前控件
     * 2保存测量结果
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("ChinaMapView", "onMeasure()");
        if (!isFinishedParse) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //计算出缩放比
        int realWidth = 0;
        int realHeight = 0;
        if (totalRectF != null) {
            float originWidth = totalRectF.width();
            realWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : (int) totalRectF.width();
            realHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : (int) totalRectF.height();
            scale = realWidth / originWidth;
        } else {
            realWidth = widthMode;
            realHeight = heightMode;
        }
        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("ChinaMapView", "onDraw()");
        if (!isFinishedParse) {
            return;
        }
        canvas.save();
        //对地图进行缩放
        canvas.scale(scale, scale);
        //绘制所有的省份path
        for (Province province : provinceList) {
            paint.setColor(province.fillColor);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(province.path, paint);
            paint.setColor(province.strokeColor);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(province.path, paint);
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO: 2020/6/9 实现省份点击监听（课后作业）
        float x = event.getX();
        float y = event.getY();
//        RectF rectF = new RectF();
//        boolean contains = rectF.contains(x, y);
        return true;
    }
}
