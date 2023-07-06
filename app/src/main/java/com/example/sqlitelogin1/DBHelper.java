package com.example.sqlitelogin1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "Loin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table Users(Username TEXT primary key, Password TEXT, Email Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists Users");
    }

    public Boolean insertUsers (String Username, String Password, String Email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Username, "Username");
        contentValues.put(Password, "Password");
        contentValues.put(Email,"Email");
        long result =myDB.insert("Users", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean check_Username (String Username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from Users where username = ?", new String[]{Username});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean  check_UserPass ( String Username, String Password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from Users where username = ? and password =?", new String[]{Username,Password});

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

}
