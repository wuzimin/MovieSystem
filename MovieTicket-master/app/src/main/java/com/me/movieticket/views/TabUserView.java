package com.me.movieticket.views;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.me.movieticket.R;
import com.me.movieticket.adapter.OrderAdapter;
import com.me.movieticket.database.DataOperate;
import com.me.movieticket.database.DataOrder;

import java.util.List;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class TabUserView extends RelativeLayout {
    public static TabUserView instance;
    private ListView lv_list;
    private SQLiteDatabase dbReader;
    private SQLiteDatabase dbWriter;
    private DataOrder dataOrder;
    private Cursor cursor;
    private OrderAdapter orderAdapter;

    public TabUserView(Context context) {
        super(context);
    }

    public TabUserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabUserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        instance = this;
        initView();

    }

    private void initView() {
        dataOrder = new DataOrder(getContext());
        lv_list = (ListView) findViewById(R.id.lv_list);
    }

    public void userResume() {

        dbReader = dataOrder.getReadableDatabase();
        cursor = dbReader.query(DataOrder.TABLE_NAME_ORDER, null,null,null,
                null,null,null,null);
        orderAdapter = new OrderAdapter(getContext(), cursor);
        lv_list.setAdapter(orderAdapter);
        dbReader.close();
    }

    public void cancelOrder(int _id, String seatInfo, String movieName, String movieTime) {
        dbWriter = dataOrder.getWritableDatabase();
        dbWriter.delete(DataOrder.TABLE_NAME_ORDER, "_id=?",
                new String[]{String.valueOf(_id)});
        dbWriter.close();
        userResume();

        changeSeat(seatInfo,movieName,movieTime);
    }

    private void changeSeat(String seatInfo, String movieName, String movieTime) {
        DataOperate db =  new DataOperate();
        db.deleteCancelSeat(seatInfo, movieName, movieTime);
    }
}
