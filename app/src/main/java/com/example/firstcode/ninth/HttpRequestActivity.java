package com.example.firstcode.ninth;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstCode.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequestActivity extends AppCompatActivity {
    TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_request);
        Button btRequest = findViewById(R.id.bt_request);
        btRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpUrlConnection();
            }
        });
        tvContent = findViewById(R.id.tv_content);
    }

    /**
     * HttpUrlConnection 请求
     */
    public void httpUrlConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader bufferedReader = null;
                HttpURLConnection urlConnection = null;
                try {
                    // 获取 HttpUrlConnection 实例
                    URL url = new URL("http://wwww.baidu.com");
                     urlConnection = (HttpURLConnection) url.openConnection();

                    // 设置 Http 请求所使用的方法
                    urlConnection.setRequestMethod("GET");
                    // 自由定制一些细节
                    urlConnection.setConnectTimeout(8000);
                    urlConnection.setReadTimeout(8000);
                    // 请求头
                    urlConnection.setRequestProperty("header","value");
                    // 获取 浏览器返回的输入流
                    InputStream in = urlConnection.getInputStream();
                    // 下面对获取的输入流进行读取
                    bufferedReader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line =bufferedReader.readLine())!=null){
                        sb.append(line);
                    }
                    shwoResponse(sb.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (bufferedReader!=null){
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection !=null){
                        urlConnection.disconnect();
                    }
                }
            }
        }).start();

    }


    /**
     * 显示到页面上
     * @param response 请求内容
     */
    public void shwoResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行 UI 操作，将结果显示到界面上
                tvContent.setText(response);
            }
        });
    }
}
