package com.me.movieticket.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.me.movieticket.common.CommonFunc;
import com.me.movieticket.entity.MovieInfo;
import com.me.movieticket.entity.TicketItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public class DataOperate {
    public SQLiteDatabase db;

    public DataOperate() {
        db = SQLiteDatabase.openDatabase(DataInit.DB_PATH+DataInit.DB_MOVIE_INFO,
                null, SQLiteDatabase.OPEN_READONLY);
    }

    public List<MovieInfo> getMovieInfo() {
        List<MovieInfo> list = new ArrayList<MovieInfo>();

        Cursor cursor = db.rawQuery("select * from movieInfo", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();

            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                MovieInfo movieInfo = new MovieInfo();

                movieInfo.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                movieInfo.setMovie_name(cursor.getString(cursor.getColumnIndex("movie_name")));
                movieInfo.setMovie_rate(cursor.getString(cursor.getColumnIndex("movie_rate")));
                movieInfo.setShort_introduction(cursor.getString(cursor.getColumnIndex("short_introduction")));
                movieInfo.setLong_introduction(cursor.getString(cursor.getColumnIndex("long_introduction")));
                movieInfo.setMovie_price(cursor.getString(cursor.getColumnIndex("movie_price")));
                movieInfo.setMovie_path(cursor.getString(cursor.getColumnIndex("movie_path")));
                list.add(movieInfo);
            }
        }
        db.close();
        return list;
    }

    public List<TicketItem> getTicketInfo(String movieName) {
        db = SQLiteDatabase.openDatabase(DataInit.DB_PATH+DataInit.DB_TICKET_INFO,
                null, SQLiteDatabase.OPEN_READONLY);
        List<TicketItem> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from ticketInfo", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();

            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                if (movieName.equals(cursor.getString(cursor.getColumnIndex("movie_name")))) {
                    TicketItem ticketItem = new TicketItem();
                    ticketItem.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                    ticketItem.setOrderedSeat(cursor.getString(cursor.getColumnIndex("orderedSeat")));
                    ticketItem.setMovie_name(cursor.getString(cursor.getColumnIndex("movie_name")));
                    ticketItem.setMovie_time(cursor.getString(cursor.getColumnIndex("movie_time")));
                    ticketItem.setMovie_location(cursor.getString(cursor.getColumnIndex("movie_location")));
                    ticketItem.setMovie_price(cursor.getString(cursor.getColumnIndex("movie_price")));
                    list.add(ticketItem);
                }
            }
        }
        db.close();
        return list;
    }

    public void updateSeat(int _id, List<Integer> seat, List<Integer> last_seat) {
        Log.i("已选座位：", seat.toString());
        db = SQLiteDatabase.openDatabase(DataInit.DB_PATH+DataInit.DB_TICKET_INFO,
                null, SQLiteDatabase.OPEN_READWRITE);
        if (last_seat == null) {
            last_seat = new ArrayList<>();
        }
        for (Integer i: seat) {
            last_seat.add(i);
        }

        ContentValues cv = new ContentValues();
        cv.put("orderedSeat", last_seat.toString());
        db.update("ticketInfo", cv, "_id=?",
                new String[]{String.valueOf(_id)});

        db.close();
    }

    public void deleteCancelSeat(String seatInfo, String movieName, String movieTime) {
        Log.i("修改", seatInfo+movieName+movieTime);
        db = SQLiteDatabase.openDatabase(DataInit.DB_PATH+DataInit.DB_TICKET_INFO,
                null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from ticketInfo", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                if (movieName.equals(cursor.getString(cursor.getColumnIndex("movie_name")))
                        && movieTime.equals(cursor.getString(cursor.getColumnIndex("movie_time"))))
                {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    List<Integer> cancelSeat = CommonFunc.changeStringToList(seatInfo);
                    String hasSeat = cursor.getString(cursor.getColumnIndex("orderedSeat"));
                    List<Integer> hSeat = CommonFunc.changeStringToList(hasSeat);
                    Log.i("修改", cancelSeat.toString()+"cancel");
                    Log.i("修改", hSeat.toString()+"has");

                    for (int k = 0; k < cancelSeat.size(); k++) {
                        for (int m = 0; m < hSeat.size(); m++) {
                            if (cancelSeat.get(k).intValue() == hSeat.get(m).intValue()) {
                                hSeat.remove(m);
                                break;
                            }
                        }
                    }
                    String storeSeat="";
                    if (hSeat.size() <= 0) {
                        storeSeat = "null";
                    } else {
                        storeSeat = hSeat.toString();
                    }
                    ContentValues cv = new ContentValues();
                    cv.put("orderedSeat", storeSeat);
                    db.update("ticketInfo", cv, "_id=?",
                            new String[]{String.valueOf(id)});
                    return;
                }
            }
        }
        db.close();
    }

}
