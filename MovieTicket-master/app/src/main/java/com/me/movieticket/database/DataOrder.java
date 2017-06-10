package com.me.movieticket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class DataOrder extends SQLiteOpenHelper {
    public static final String TABLE_NAME_ORDER = "orders";
    public static final String ID = "_id";
    public static final String MOVIE_NAME = "movie_name";
    public static final String MOVIE_TIME = "movie_time";
    public static final String MOVIE_LOCATION = "movie_location";
    public static final String MOVIE_SEAT = "movie_seat";
    public static final String MOVIE_MONEY = "movie_money";
    public static final int VERSION = 1;

    public DataOrder(Context context) {
        super(context, TABLE_NAME_ORDER, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ORDER + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MOVIE_NAME + " TEXT NOT NULL,"
                + MOVIE_TIME + " TEXT NOT NULL,"
                + MOVIE_LOCATION + " TEXT NOT NULL,"
                + MOVIE_SEAT + " TEXT NOT NULL,"
                + MOVIE_MONEY + " TEXT NOT NULL"
                +" )";
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
