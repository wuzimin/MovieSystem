package com.me.movieticket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.me.movieticket.R;
import com.me.movieticket.adapter.MovieTickerAdapter;
import com.me.movieticket.database.DataOperate;
import com.me.movieticket.entity.MovieInfo;
import com.me.movieticket.entity.TicketItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieInfoActivity extends AppCompatActivity {
    public static MovieInfoActivity instance = null;

    private ImageButton ib_order_quick;
    private ImageButton ib_back;
    private ListView lv_list;
    private RelativeLayout rl_list_view;
    private RelativeLayout rl_movie_info;

    private MovieInfo movieInfo;

    private ImageView iv_movie_img;
    private TextView tv_movie_name;
    private TextView tv_movie_rate;
    private TextView tv_movie_introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        instance = this;
        //接受电影信息
        Intent intent = this.getIntent();
        movieInfo = (MovieInfo)intent.getSerializableExtra("movie");
        initView();
    }

    private void initView() {
        ib_order_quick = (ImageButton) findViewById(R.id.ib_order_quick);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        lv_list = (ListView) findViewById(R.id.lv_list);
        rl_movie_info = (RelativeLayout) findViewById(R.id.rl_movie_info);
        rl_list_view = (RelativeLayout) findViewById(R.id.rl_list_view);

        initInfo();

        ib_order_quick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_movie_info.setVisibility(View.INVISIBLE);
                rl_list_view.setVisibility(View.VISIBLE);
            }
        });

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieInfoActivity.this, MainActivity.class);
                startActivity(intent);
                MovieInfoActivity.this.finish();
            }
        });

        List<TicketItem> list = getData();
        MovieTickerAdapter movieTickerAdapter = new MovieTickerAdapter(this, list);
        lv_list.setAdapter(movieTickerAdapter);
    }

    private void initInfo() {
        iv_movie_img = (ImageView) findViewById(R.id.iv_movie_img);
        tv_movie_name = (TextView) findViewById(R.id.tv_movie_name);
        tv_movie_rate = (TextView) findViewById(R.id.tv_movie_rate);
        tv_movie_introduction = (TextView) findViewById(R.id.tv_movie_introduction);

        //放射机制根据数据库名字加载图片
        String movie_path = movieInfo.getMovie_path();
        Class mip = R.mipmap.class;
        Field field = null;
        try {
            field = mip.getField(movie_path);
            int res_ID = field.getInt(field.getName());
            iv_movie_img.setImageResource(res_ID);
        } catch (Exception e) {}
        tv_movie_name.setText(movieInfo.getMovie_name());
        tv_movie_rate.setText(movieInfo.getMovie_rate());
        tv_movie_introduction.setText(movieInfo.getLong_introduction());
    }

    private List<TicketItem> getData() {
        DataOperate dbOperate = new DataOperate();
        List<TicketItem> list = dbOperate.getTicketInfo(movieInfo.getMovie_name());
        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
