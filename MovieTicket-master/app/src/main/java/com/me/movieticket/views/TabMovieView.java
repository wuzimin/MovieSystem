package com.me.movieticket.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.me.movieticket.R;
import com.me.movieticket.adapter.MovieShowAdapter;
import com.me.movieticket.database.DataInit;
import com.me.movieticket.database.DataOperate;
import com.me.movieticket.entity.MovieInfo;

import java.util.List;

/**
 * Created by qwtangwenqiang on 2016/6/5.
 */
public class TabMovieView extends RelativeLayout {
    private ListView lv_list;
    public TabMovieView(Context context) {
        super(context);
    }

    public TabMovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabMovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        lv_list = (ListView) findViewById(R.id.lv_list);

        List<MovieInfo> list = getMovieData();
        MovieShowAdapter movieShowAdapter = new MovieShowAdapter(getContext() ,list);
        lv_list.setAdapter(movieShowAdapter);
    }

    private List<MovieInfo> getMovieData() {
        DataOperate dbOperate = new DataOperate();
        List<MovieInfo> list = dbOperate.getMovieInfo();

        return list;
    }
}
