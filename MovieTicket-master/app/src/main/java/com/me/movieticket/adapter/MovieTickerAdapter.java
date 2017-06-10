package com.me.movieticket.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.activity.ChooseSeatActivity;
import com.me.movieticket.activity.MovieInfoActivity;
import com.me.movieticket.entity.TicketItem;

import java.util.List;
import java.util.Map;

/**
 * Created by qwtangwenqiang on 2016/6/5.
 */
public class MovieTickerAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<TicketItem> list;

    public MovieTickerAdapter(Context context, List<TicketItem> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.movie_ticket_item, null);
            viewHolder.tv_movie_time = (TextView) convertView.findViewById(R.id.tv_movie_time);
            viewHolder.tv_movie_location = (TextView) convertView.findViewById(R.id.tv_movie_location);
            viewHolder.tv_movie_price = (TextView) convertView.findViewById(R.id.tv_movie_price);
            viewHolder.ib_choose_seat = (ImageButton) convertView.findViewById(R.id.ib_choose_seat);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_movie_time.setText(list.get(position).getMovie_time());
        viewHolder.tv_movie_location.setText(list.get(position).getMovie_location());
        viewHolder.tv_movie_price.setText(list.get(position).getMovie_price());
        viewHolder.ib_choose_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                TicketItem ticketItem = (TicketItem)list.get(position);
                bundle.putSerializable("ticketItem", ticketItem);
                intent.putExtras(bundle);
                intent.setClass(context, ChooseSeatActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        public TextView tv_movie_time;
        public TextView tv_movie_location;
        public TextView tv_movie_price;
        public ImageButton ib_choose_seat;
    }
}
