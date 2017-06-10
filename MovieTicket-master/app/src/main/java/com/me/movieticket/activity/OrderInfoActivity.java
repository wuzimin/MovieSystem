package com.me.movieticket.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.database.DataOrder;
import com.me.movieticket.entity.TicketItem;

import java.util.List;

public class OrderInfoActivity extends AppCompatActivity {
    private ImageButton ib_back;
    private TicketItem ticketItem;
    private List<Integer> cur_order;

    private TextView tv_movie_name;
    private TextView tv_movie_time;
    private TextView tv_movie_location;
    private TextView tv_movie_seat;
    private TextView tv_all_price;

    private DataOrder db_order;
    private SQLiteDatabase db_writter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        //接受电影票信息和当前预订座位
        initData();

        initView();

        storeOrder();
    }

    private void initData() {
        Intent intent = this.getIntent();
        ticketItem = (TicketItem)intent.getSerializableExtra("ticket");
        cur_order = intent.getIntegerArrayListExtra("cur_order");
        Log.i("order", cur_order.toString());
    }

    private void initView() {
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderInfoActivity.this.finish();
            }
        });

        tv_movie_name = (TextView) findViewById(R.id.tv_movie_name);
        tv_movie_time = (TextView) findViewById(R.id.tv_movie_time);
        tv_movie_location = (TextView) findViewById(R.id.tv_movie_location);
        tv_movie_seat = (TextView) findViewById(R.id.tv_movie_seat);
        tv_all_price = (TextView) findViewById(R.id.tv_all_price);

        tv_movie_name.setText(ticketItem.getMovie_name());
        tv_movie_time.setText(ticketItem.getMovie_time());
        tv_movie_location.setText(ticketItem.getMovie_location());
        tv_movie_seat.setText(cur_order.toString());
        String price = ticketItem.getMovie_price();
        Log.i("price", price);
        String int_price = price.replace("元","");
        Log.i("prices", int_price);
        int money = Integer.parseInt(int_price);
        money = cur_order.size()*money;
        tv_all_price.setText(money+"元");

        db_order = new DataOrder(this);
        db_writter = db_order.getWritableDatabase();
    }

    private void storeOrder() {
        db_order = new DataOrder(this);
        db_writter = db_order.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataOrder.MOVIE_NAME, tv_movie_name.getText().toString());
        cv.put(DataOrder.MOVIE_TIME, tv_movie_time.getText().toString());
        cv.put(DataOrder.MOVIE_LOCATION, tv_movie_location.getText().toString());
        cv.put(DataOrder.MOVIE_SEAT, tv_movie_seat.getText().toString());
        cv.put(DataOrder.MOVIE_MONEY, tv_all_price.getText().toString());
        db_writter.insert(DataOrder.TABLE_NAME_ORDER, null, cv);

        Toast.makeText(this, "预订成功！", Toast.LENGTH_SHORT).show();
        db_writter.close();
    }
}
