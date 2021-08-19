package com.example.firstcode.sixth_chapter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstCode.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * 说明：文件存储
 * <p>
 * date: 2019-12-09 21:22
 *
 * @author syd
 * @version 1.0
 */
public class FileSaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Button btFileSave = findViewById(R.id.bt_file_save);
        btFileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outputStream =
                            FileSaveActivity.this.openFileOutput("save", MODE_PRIVATE);

                    BufferedWriter bufferedWriter = new BufferedWriter(
                            new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                    bufferedWriter.write("你好啊啊，哈哈哈哈");

                    bufferedWriter.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btFileRead = findViewById(R.id.bt_file_read);
        btFileRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput("save");
                    BufferedReader bufferedReader = null;
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    String line = "";
                    while ((line = bufferedReader.readLine())!= null){
                        sb.append(line);
                    }
                    EditText editText = findViewById(R.id.et_info);
                    if (!TextUtils.isEmpty(sb.toString())){
                        editText.setText(sb.toString());
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
