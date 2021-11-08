package com.haui.phamdai.sqlitesaveimagekpt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void insertDoVat(String ten, String moTa, byte[] hinh) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO DoVat VALUES(null,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        // clear dữ liệu đã phân tích từ trước
        statement.clearBindings();

        statement.bindString(1, ten);
        statement.bindString(2, moTa);
        statement.bindBlob(3, hinh);

        // thực thi insert
        statement.executeInsert();
    }

    public void updateDoVat(DoVat doVat) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE DoVat SET Ten = ?, MoTa = ?, HinhAnh = ? WHERE Id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        // clear dữ liệu đã phân tích từ trước
        statement.clearBindings();

        statement.bindString(1, doVat.getTen());
        statement.bindString(2, doVat.getMoTa());
        statement.bindBlob(3, doVat.getHinhAnh());
        statement.bindLong(4, doVat.getId());

        // thực thi update
        statement.executeUpdateDelete();
    }

    public void queryData(String query) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
    }

    public Cursor getData(String query) {
        return getReadableDatabase().rawQuery(query, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
