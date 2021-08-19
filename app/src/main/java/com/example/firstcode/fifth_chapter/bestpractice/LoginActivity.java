package com.example.firstcode.fifth_chapter.bestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.firstCode.R;

/**
 * 说明：$
 * <p>
 * date: 2019-12-02 22:14
 *
 * @author syd
 * @version 1.0
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText etUserName = findViewById(R.id.et_name);
        final EditText etPassword = findViewById(R.id.et_password);
        Button bt = findViewById(R.id.bt_login);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                if ("zhansan".equals(username)&&"123".equals(password)){
                    Intent intent = new Intent(LoginActivity.this,SuccessActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,"不正确",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
