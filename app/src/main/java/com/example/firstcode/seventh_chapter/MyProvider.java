package com.example.firstcode.seventh_chapter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 说明：自己的内容提供者
 * <p>
 * date: 2020-02-27 18:37
 *
 * @author syd
 * @version 1.0
 */
public class MyProvider extends ContentProvider {
    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    private static UriMatcher uriMatcher;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.xx.xx","table1",TABLE1_DIR);
        uriMatcher.addURI("com.xx.xx","table1/#",TABLE1_ITEM);
        uriMatcher.addURI("com.xx.xx","table2",TABLE2_DIR);
        uriMatcher.addURI("com.xx.xx","table2/#",TABLE2_ITEM);
    }
    @Override
    public boolean onCreate() {

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                // 查询 table1 表中的所有数据
                break;
            case TABLE1_ITEM:
                // 查询 table1 表中的单条数据
                break;

        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.xx.xx.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.xx.xx.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.xx.xx.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.xx.xx.table2";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
