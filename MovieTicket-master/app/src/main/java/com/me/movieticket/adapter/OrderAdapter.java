package com.me.movieticket.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.database.DataOrder;
import com.me.movieticket.views.TabUserView;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class OrderAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    LayoutInflater inflater;
    public OrderAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_order, null);
            viewHolder.tv_movie_name = (TextView) convertView.findViewById(R.id.tv_movie_name);
            viewHolder.tv_movie_time = (TextView) convertView.findViewById(R.id.tv_movie_time);
            viewHolder.tv_movie_location = (TextView) convertView.findViewById(R.id.tv_movie_location);
            viewHolder.tv_movie_seat = (TextView) convertView.findViewById(R.id.tv_movie_seat);
            viewHolder.tv_movie_price = (TextView) convertView.findViewById(R.id.tv_movie_price);
            viewHolder.ib_cancel = (ImageButton) convertView.findViewById(R.id.ib_cancel);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        cursor.moveToPosition(position);
        viewHolder.tv_movie_name.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_NAME)));
        viewHolder.tv_movie_time.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_TIME)));
        viewHolder.tv_movie_location.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_LOCATION)));
        viewHolder.tv_movie_seat.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_SEAT)));
        viewHolder.tv_movie_price.setText(cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_MONEY)));

        final int id = cursor.getInt(cursor.getColumnIndex(DataOrder.ID));
        final String seatInfo = cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_SEAT));
        final String movieName = cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_NAME));
        final String movieTime = cursor.getString(cursor.getColumnIndex(DataOrder.MOVIE_TIME));
        viewHolder.ib_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "取消了订单", Toast.LENGTH_SHORT).show();
                TabUserView.instance.cancelOrder(id, seatInfo, movieName, movieTime);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        public TextView tv_movie_name;
        public TextView tv_movie_time;
        public TextView tv_movie_location;
        public TextView tv_movie_seat;
        public TextView tv_movie_price;
        public ImageButton ib_cancel;
    }
}
