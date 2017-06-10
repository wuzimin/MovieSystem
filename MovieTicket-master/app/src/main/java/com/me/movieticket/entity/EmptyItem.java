package com.me.movieticket.entity;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public class EmptyItem extends AbstractItem {
    public EmptyItem(String label, Boolean isOrdered) {
        super(label, isOrdered);
    }


    @Override
    public int getType() {
        return TYPE_EMPTY;
    }
}
