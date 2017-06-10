package com.me.movieticket.entity;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public class CenterItem extends AbstractItem {
    public CenterItem(String label, Boolean isOrdered) {
        super(label, isOrdered);
    }

    @Override
    public int getType() {
        return TYPE_CENTER;
    }
}
