package com.example.firstcode.seventh_chapter.contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.firstCode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：展示联系人信息
 * <p>
 * date: 2020-02-27 17:59
 *
 * @author syd
 * @version 1.0
 */
public class ContactsActivity extends AppCompatActivity {
    ListView lv;
    List<String> contactsList = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        lv = findViewById(R.id.lv_contacts);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        lv.setAdapter(arrayAdapter);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);

        }else {
            readContacts();
        }

    }

    private void readContacts(){
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if (cursor!=null){
                while (cursor.moveToNext()){
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String  number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName + "\n" + number);
                }
                arrayAdapter.notifyDataSetChanged();

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else {
                    Toast.makeText(this,"no permission",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
