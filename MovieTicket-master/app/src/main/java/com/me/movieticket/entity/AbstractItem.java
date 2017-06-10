package com.me.movieticket.entity;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public abstract class AbstractItem {
    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;

    private String label;
    private Boolean isOrdered;


    public AbstractItem(String label, Boolean isOrdered) {
        this.isOrdered = isOrdered;
        this.label = label;
    }


    public String getLabel() {
        return label;
    }

    public Boolean getIsOrdered() {return isOrdered;}

    abstract public int getType();
}



