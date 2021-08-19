package com.example.firstcode.test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.firstCode.R;

/**
 * 说明：$
 * <p>
 * date: 2020-06-09 23:21
 *
 * @author syd
 * @version 1.0
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }
}
