package com.me.movieticket.entity;

import java.io.Serializable;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public class MovieInfo implements Serializable{
    private int _id;
    private String movie_name;
    private String movie_rate;
    private String short_introduction;
    private String long_introduction;
    private String movie_price;
    private String movie_path;

    public String getMovie_path() {
        return movie_path;
    }

    public void setMovie_path(String movie_path) {
        this.movie_path = movie_path;
    }

    public MovieInfo(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_rate() {
        return movie_rate;
    }

    public void setMovie_rate(String movie_rate) {
        this.movie_rate = movie_rate;
    }

    public String getShort_introduction() {
        return short_introduction;
    }

    public void setShort_introduction(String short_introduction) {
        this.short_introduction = short_introduction;
    }

    public String getLong_introduction() {
        return long_introduction;
    }

    public void setLong_introduction(String long_introduction) {
        this.long_introduction = long_introduction;
    }

    public String getMovie_price() {
        return movie_price;
    }

    public void setMovie_price(String movie_price) {
        this.movie_price = movie_price;
    }
}
