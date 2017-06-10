package com.me.movieticket.entity;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class OrderItem {
    private String movie_name;
    private String movie_time;
    private String movie_location;
    private String movie_seat;
    private String movie_money;

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(String movie_time) {
        this.movie_time = movie_time;
    }

    public String getMovie_location() {
        return movie_location;
    }

    public void setMovie_location(String movie_location) {
        this.movie_location = movie_location;
    }

    public String getMovie_seat() {
        return movie_seat;
    }

    public void setMovie_seat(String movie_seat) {
        this.movie_seat = movie_seat;
    }

    public String getMovie_money() {
        return movie_money;
    }

    public void setMovie_money(String movie_money) {
        this.movie_money = movie_money;
    }
}
