package com.me.movieticket.entity;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public class EdgeItem extends AbstractItem{
    public EdgeItem(String label, Boolean isOrdered) {
        super(label, isOrdered);
    }



    @Override
    public int getType() {
        return TYPE_EDGE;
    }
}
