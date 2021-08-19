package com.example.firstcode.eighth_chapter;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.firstCode.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 说明：$
 * <p>
 * date: 2020-03-09 11:23
 *
 * @author syd
 * @version 1.0
 */
public class MediaActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView picture;
    private Uri imageUri;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_media);
        Button btTakePhone = findViewById(R.id.bt_take_photo);
        picture = findViewById(R.id.iv_picture);
        // 调用相机
        btTakePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImg = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImg.exists()) {
                        outputImg.delete();
                    }
                    outputImg.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(MediaActivity.this, "com.example.firstcode", outputImg);
                } else {
                    imageUri = Uri.fromFile(outputImg);
                }
                // 调用相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        // 从相册选择图片
        Button btChoosePhoto = findViewById(R.id.bt_choose_photo);
        btChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MediaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MediaActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });

        // 播放音乐
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        } else {
            // 初始化 MediaPlayer
            initMediaPlayer();
        }
        Button btPlay = findViewById(R.id.bt_play);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {

                    mediaPlayer.start();
                }
            }
        });

        Button btPause = findViewById(R.id.bt_pause);
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });
        Button btStop = findViewById(R.id.bt_stop);
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);

    }


    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(), "MySongs");
        if (file.exists()) {
            File file1 = new File(file, "房东的猫-秋酿.mp3");
            try {
                mediaPlayer.setDataSource(file1.getPath());
                // 进入准备状态
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (grantResults.length>0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4 及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //  4.4 以下系统使用这个方法处理图片
                        handleImageBeforKitKat(data);
                    }
                }
                break;
        }
    }

    /**
     * Android 4.4 及以上 相册选择图片 后处理
     *
     * @param data Intent
     */
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是 document 类型的 uri 则通过 document id 处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的 id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 content 类型的 Uri 则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 File 类型的Uri ，直接获取图片路径即可
            imagePath = uri.getPath();

        }
        // 根据图片路径显示图片
        displayImage(imagePath);
    }

    /**
     * Android 4.4 以下处理方式
     *
     * @param data Intent
     */
    private void handleImageBeforKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    /**
     * 获取真实的图片 地址
     *
     * @param uri       图片 uri
     * @param selection 条件
     * @return 图片地址
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过 Uri 和 selection 来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    public void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

}
