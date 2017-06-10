package com.me.movieticket.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.activity.MovieInfoActivity;
import com.me.movieticket.entity.MovieInfo;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by qwtangwenqiang on 2016/6/5.
 */
public class MovieShowAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private Cursor cursor;
    private RelativeLayout layout;
    private List<MovieInfo> list;

    public MovieShowAdapter(Context context, List<MovieInfo> list) {
        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        //获取数据集中与指定索引对应的数据项
        return position;
    }

    @Override
    public long getItemId(int position) {
        //获取在列表中与指定索引对应的行id
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.movie_list_item, null);
            viewHolder.iv_movie_img = (ImageView) convertView.findViewById(R.id.iv_movie_img);
            viewHolder.tv_movie_name = (TextView) convertView.findViewById(R.id.tv_movie_name);
            viewHolder.tv_movie_info = (TextView) convertView.findViewById(R.id.tv_movie_info);
            viewHolder.tv_movie_rate = (TextView) convertView.findViewById(R.id.tv_movie_rate);
            viewHolder.ib_movie_order = (ImageButton) convertView.findViewById(R.id.ib_movie_order);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //放射机制根据数据库名字加载图片
        String movie_path = list.get(position).getMovie_path();
        Class mip = R.mipmap.class;
        Field field = null;
        try {
            field = mip.getField(movie_path);
            int res_ID = field.getInt(field.getName());
            viewHolder.iv_movie_img.setImageResource(res_ID);
        } catch (Exception e) {}
        viewHolder.tv_movie_name.setText(list.get(position).getMovie_name());
        viewHolder.tv_movie_info.setText(list.get(position).getShort_introduction());
        viewHolder.tv_movie_rate.setText(list.get(position).getMovie_rate());
        viewHolder.ib_movie_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieInfoActivity.class);
                Bundle bundle = new Bundle();
                MovieInfo movieInfo = list.get(position);
                bundle.putSerializable("movie", movieInfo);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        public ImageView iv_movie_img;
        public TextView tv_movie_name;
        public TextView tv_movie_info;
        public TextView tv_movie_rate;
        public ImageButton ib_movie_order;
    }
}
