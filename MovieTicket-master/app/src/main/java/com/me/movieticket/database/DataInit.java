package com.me.movieticket.database;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public class DataInit {
    public static final String DB_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath()
            +"/data/data/com.me.movieticket/databases/";
    public static final String DB_MOVIE_INFO = "movieInfo.db";
    public static final String DB_TICKET_INFO = "ticketInfo.db";

    private Context context;
    public DataInit(Context context) {
        this.context = context;
        initDataBase(DB_MOVIE_INFO);
        initDataBase(DB_TICKET_INFO);
    }

    private void initDataBase(String dbName) {
        Log.i("数据库","初始化");
        File file = new File(DB_PATH+dbName);
        if (file.exists() == false) {
            File dir = new File(DB_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
                Log.i("文件创建：", "成功");
                Log.i("文件路径", file.toString());
            }

            try {
                InputStream is = context.getAssets().open(dbName.toString());
                OutputStream os = new FileOutputStream(DB_PATH+dbName);
                byte [] buffer = new byte[10480];
                int length;

                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();     //强制输出缓冲区的内容，避免丢失数据
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("文件创建：", "已经存在");
        }
    }
}
