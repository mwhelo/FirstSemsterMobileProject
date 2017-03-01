package com.example.x1243.musicappmobiledevproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by x1243 on 12/12/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "playlist.db";
    public static final String TABLE_NAME = "playlist_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SONG";
    public static final String COL_3 = "ARTIST";
    public static final String COL_4 = "GENRE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, SONG TEXT, ARTIST TEXT, GENRE TEXT) ");


    }

    public Integer deleteSong(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);


    }

    public boolean insertData(String song, String artist, String genre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, song);
        contentValues.put(COL_3, artist);
        contentValues.put(COL_4, genre);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getPlaylist(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updatePlaylist(String id,String song, String artist, String genre ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, song);
        contentValues.put(COL_3, artist);
        contentValues.put(COL_4, genre);

        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{id});

        return true;



    }
}
