package com.me.movieticket.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public class TicketItem implements Serializable {
    private int _id;
    private String movie_name;
    private String movie_time;
    private String movie_location;
    private String movie_price;
    private String orderedSeat;
    private List<Integer> order_list;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getOrderedSeat() {
        return orderedSeat;
    }

    public void setOrderedSeat(String orderedSeat) {
        this.orderedSeat = orderedSeat;
        Log.i("大小", orderedSeat.length()+"");
        if ("null".equals(orderedSeat)) {
            Log.i("空", "null");
            setOrder_list(null);
        } else {
            List<Integer> list = new ArrayList<>();
            String temp1 = orderedSeat.replace("[","");
            String temp2 = temp1.replace("]", "");
            String temp3 = temp2.replaceAll(" ","");
            String[] seats = temp3.split(",");

            for (int i = 0; i < seats.length; i++) {
                Log.i("seat:", seats[i]);
                Integer it = Integer.valueOf(seats[i]);
                list.add(it);
            }
            setOrder_list(list);
        }
    }

    public List<Integer> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Integer> order_list) {
        this.order_list = order_list;
    }

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

    public String getMovie_price() {
        return movie_price;
    }

    public void setMovie_price(String movie_price) {
        this.movie_price = movie_price;
    }
}
