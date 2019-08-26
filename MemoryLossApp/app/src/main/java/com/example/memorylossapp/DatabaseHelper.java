package com.example.memorylossapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper { //SQLiteOpenHelter - main class for handling SQLite
    public static final String DATABASE_NAME = "patient.db";
    public static final String TABLE_NAME = "patient_table";
    public static final String COL_1_ID = "ID";
    public static final String COL_2_NAME = "NAME";
    public static final String COL_3_USERNAME = "Username";
    public static final String COL_4_PASSWORD = "Password";
    public static final String COL_5_EMAIL = "Email";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDB) {
        sqLiteDB.execSQL("create table " + TABLE_NAME + "  (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, USERNAME TEXT, PASSWORD TEXT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDB, int i, int i1) {
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDB);
    }

    public boolean insertData(String name, String username, String password, String email) {
        //initialize DB
        SQLiteDatabase sqLiteDB = this.getWritableDatabase();

        //putting parameters into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_NAME, name);
        contentValues.put(COL_3_USERNAME, username);
        contentValues.put(COL_4_PASSWORD, password);
        contentValues.put(COL_5_EMAIL, email);
        long result = sqLiteDB.insert(TABLE_NAME, null, contentValues);

        //checking if inserting data was successful or not (^ will return -1 if not)
        if (result == -1) {
            return false;
        } else
            return true;

    }
}
