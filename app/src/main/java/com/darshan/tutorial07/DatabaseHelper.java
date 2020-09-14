package com.darshan.tutorial07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="Registration";
    public static final String COL_1="ID";
    public static final String COL_2="FNAME";
    public static final String COL_3="LNAME";
    public static final String COL_4="EMAIL";
    public static final String COL_5="PASSWORD";
    public static final String COL_6="CEIT";
    public static final String COL_7="GENDER";
    public static final String COL_8="CITY";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +" (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_2 +" TEXT, "+ COL_3 +" TEXT, " + COL_4 + " TEXT, "+ COL_5 +" TEXT, "+ COL_6 +" TEXT, "+ COL_7 + " TEXT, "+ COL_8 + " TEXT)");
    }
    public boolean InsertData(String fname,String lname,String email,String password,String ceit,String gender,String city){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,fname);
        values.put(COL_3,lname);
        values.put(COL_4,email);
        values.put(COL_5,password);
        values.put(COL_6,ceit);
        values.put(COL_7,gender);
        values.put(COL_8,city);
        return db.insert(TABLE_NAME,null,values) != -1;
    }
    public boolean checkUserExist(String email, String password){
        String[] columns = {"email"};
        SQLiteDatabase db = getWritableDatabase();

        String selection = "email=? and password = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        close();
        return count > 0;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
