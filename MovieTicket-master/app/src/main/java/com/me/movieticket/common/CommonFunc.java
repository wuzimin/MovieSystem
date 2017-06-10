package com.me.movieticket.common;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwtangwenqiang on 2016/6/7.
 */
public class CommonFunc {
    //string 格式[1,2,3]
    public static List<Integer> changeStringToList(String string) {
        List<Integer> list = new ArrayList<>();
        String s1 = string.replace("[","");
        String s2 = s1.replace("]","");
        String s3 = s2.replace(" ","");

        String[] s = s3.split(",");

        for (int i = 0; i < s.length; i++) {
            Integer it = Integer.valueOf(s[i]);
            list.add(it);
        }
        return list;
    }
}
