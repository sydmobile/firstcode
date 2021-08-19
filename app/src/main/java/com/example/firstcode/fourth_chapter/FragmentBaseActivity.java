package com.example.firstcode.fourth_chapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstCode.R;

/**
 * 说明：$
 * <p>
 * date: 2019-11-10 14:48
 *
 * @author syd
 * @version 1.0
 */
public class FragmentBaseActivity extends AppCompatActivity {
    private static final String TAG = "FragmentBaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " );
        setContentView(R.layout.activity_fragmentbase);
//        Button button = findViewById(R.id.bt);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                replaceFragment(new AnotherRightFragment());
//            }
//        });
//        replaceFragment(new RightFragment());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.replace(R.id.framelayout,fragment);
//        // 可以接受一个名字用于描述返回栈的状态，一般传入 null 即可
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
    }
}
