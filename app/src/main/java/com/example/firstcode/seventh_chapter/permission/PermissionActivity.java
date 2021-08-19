package com.example.firstcode.seventh_chapter.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.firstCode.R;

/**
 * 说明：运行时权限
 * <p>
 * date: 2020-02-27 10:47
 *
 * @author syd
 * @version 1.0
 */
public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        Button btCall = findViewById(R.id.bt_call);

        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED
                ){
                    ActivityCompat.requestPermissions(PermissionActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},1);
                }else {
                    call();
                }
            }
        });

    }

    /**
     *  拨打电话
     */
    private void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    Toast.makeText(this,"You denied the perssion",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }

    }
}
