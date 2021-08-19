package com.example.firstcode.sixth_chapter.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstCode.R;
import com.example.firstcode.sixth_chapter.db.litepal.Book;
import com.example.firstcode.sixth_chapter.db.litepal.Category;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

/**
 * 说明：数据库基础demo
 * <p>
 * date: 2020-02-17 14:10
 *
 * @author syd
 * @version 1.0
 */
public class DBDemoActivity extends AppCompatActivity {
    private MySQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initView();
    }

    private void initView() {
        dbHelper = new MySQLiteOpenHelper(this, "bookstore.db", null, 2);
        Button btCreate = findViewById(R.id.bt_one);
        btCreate.setText("创建数据库");
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();

            }
        });
        Button btInsert = findViewById(R.id.bt_two);
        btInsert.setText("插入数据");
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始插入第一条数据
                values.put("name", "The Da");
                values.put("author", "dan");
                values.put("pages", 454);
                values.put("price", 16.2);

                // 插入
                long i = db.insert("book", null, values);
                Log.e("i;", i + "");
                values.clear();
                // 开始组装第二天数据
                values.put("name", "第一行");
                values.put("author", "guo");
                long j = db.insert("book", null, values);
                Log.e("j",j+"");
            }
        });
        Button btUpdate = findViewById(R.id.bt_three);
        btUpdate.setText("更新");
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("price",10.99);
                db.update("book",contentValues,"name = ?",new String[]{"The Da"});
            }
        });

        Button btDelete = findViewById(R.id.bt_four);
        btDelete.setText("删除数据");
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                sqLiteDatabase.delete("book","pages > ?",new String []{"400"});
            }
        });
        Button btQuery = findViewById(R.id.bt_five);
        btQuery.setText("查询");
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = database.query("book",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        String  name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                       Log.e("book",name);
                       Log.e("author",author);
                    }while (cursor.moveToNext());

                }
                cursor.close();
            }
        });
        // litepal 内容
        Button btCreateDatabase = findViewById(R.id.bt_six);
        btCreateDatabase.setText("表操作");
        btCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        Button btAdd  = findViewById(R.id.bt_seven);
        btAdd.setText("添加数据");
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category();
                category.setCategoryCode(1);
                category.setCategoryName("人文科学");
                category.save();

                Book book = new Book();
                book.setAuthor("syd");
                book.setName("first");
                book.save();

            }
        });
        Button btLitePalUpdate = findViewById(R.id.bt_eight);
        btLitePalUpdate.setText("更新");
        btLitePalUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category();
                category.setCategoryCode(2);
                category.setCategoryName("社会科学");
                category.save();
                category.setCategoryName("技术类");
                if (category.isSaved()){

                    category.save();
                }
            }
        });
        Button btLitePalUpdate1 = findViewById(R.id.bt_eight_1);
        btLitePalUpdate1.setText("更新2");
        btLitePalUpdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category();
                category.setCategoryName("健康生活2");
                category.setCategoryCode(0);
                category.setToDefault("categoryCode");
                category.updateAll("id = ? ","1");

            }
        });
        Button btLitePalDelete = findViewById(R.id.bt_nine);
        btLitePalDelete.setText("删除数据");
        btLitePalDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Category.class,"id = ? ","1");
            }
        });
    }
}
