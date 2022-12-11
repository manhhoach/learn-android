package com.example.chunhat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {
    private static final String DBNAME="test.db";
    private static final int VERSION=1;
    private static final String TABLE_NAME="SINHVIEN1";

    private static String ID="_id"; // bắt buộc có
    // tương ứng với đề bài
    private static String TEN="ten";
    private static String NGAYSINH="ngaysinh";
    private static String DIEM="diem";

    private SQLiteDatabase mydb;

    public MyDB(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // nhớ để ý dấu cách
        // string: text, int: integer, double: real
        // id: giữ nguyên
        String createTable ="CREATE TABLE " +TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TEN+ " TEXT, "+NGAYSINH+" TEXT, "+DIEM+" REAL)";
        db.execSQL(createTable);
    }

    public static String getTEN() {
        return TEN;
    }

    public static String getID() {
        return ID;
    }

    public static String getNGAYSINH() {
        return NGAYSINH;
    }

    public static String getDIEM() {
        return DIEM;
    }

    public void openDb()
    {
        mydb=getWritableDatabase();
    }
    public void closeDb()
    {
        if(mydb!=null&&mydb.isOpen())
            mydb.close();
    }
    public Cursor get()
    {
        String query="SELECT * FROM "+TABLE_NAME;
        return mydb.rawQuery(query, null);
    }
    public long insert(String ten, String ngaysinh, Double diem)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(TEN, ten);
        value.put(NGAYSINH, ngaysinh);
        value.put(DIEM, diem);
        return db.insert(TABLE_NAME, null, value);
    }
    public long update(Integer id,String ten, String ngaysinh, Double diem)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(TEN, ten);
        value.put(NGAYSINH, ngaysinh);
        value.put(DIEM, diem);
        String where= ID+" = " + id;
        return db.update(TABLE_NAME, value, where, null);
    }
    public long delete(Integer id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        String where= ID+" = " + id;
        return db.delete(TABLE_NAME, where, null);
    }

}
