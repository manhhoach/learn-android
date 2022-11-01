package com.example.kiemtracuoiky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {
    private static final String DBNAME="mydb.db";
    private static final int VERSION=1;
    private static final String TABLE_NAME="DANHSACH";

    private static String TEN="ten";
    private static String ID="_id";
    private static String NGAYSINH="que";

    private SQLiteDatabase mydb;

    public MyDB(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable= "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+ TEN+" TEXT NOT NULL,"+NGAYSINH+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  String getTEN() {
        return TEN;
    }

    public  String getID() {
        return ID;
    }

    public  String getNGAYSINH() {
        return NGAYSINH;
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
    public long insert(String ten, String ngaysinh)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(TEN, ten);
        value.put(NGAYSINH, ngaysinh);
        return db.insert(TABLE_NAME, null, value);
    }
}
