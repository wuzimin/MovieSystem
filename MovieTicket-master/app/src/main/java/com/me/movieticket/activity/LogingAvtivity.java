package com.me.movieticket.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.database.DataUser;

public class LogingAvtivity extends AppCompatActivity {
    private Button login_reback_btn;
    private EditText login_user_edit;
    private EditText login_passwd_edit;
    private Button login_login_btn;
    private DataUser dataUser;
    private SQLiteDatabase dbReader;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging_avtivity);

        initView();
    }

    private void initView() {
        login_reback_btn = (Button) findViewById(R.id.login_reback_btn);
        login_user_edit = (EditText) findViewById(R.id.login_user_edit);
        login_passwd_edit = (EditText) findViewById(R.id.login_passwd_edit);
        login_login_btn = (Button) findViewById(R.id.login_login_btn);

        dataUser = new DataUser(this);
        dbReader = dataUser.getReadableDatabase();

        login_reback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogingAvtivity.this.finish();
            }
        });

        login_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = login_user_edit.getText().toString();
                String userPass = login_passwd_edit.getText().toString();

                if ("".equals(userName)) {
                    Toast.makeText(LogingAvtivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                } else if ("".equals(userPass)) {
                    Toast.makeText(LogingAvtivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    queryUser(userName, userPass);
                }
            }
        });
    }

    private void queryUser(String userName, String userPass) {
        boolean logging_sucess = false;
        cursor = dbReader.query(DataUser.TABLE_NAME_USER, null, null, null,
                null, null, null, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            if (cursor.getString(cursor.getColumnIndex(DataUser.USER_NAME)).toString()
                    .equals(userName)
                    && cursor.getString(cursor.getColumnIndex(DataUser.USER_PASSWORD)).toString()
                    .equals(userPass)) {
                logging_sucess = true;
                cursor.close();
                dbReader.close();
                goMain();
                break;
            }
        }
        if (logging_sucess == false) {
            Toast.makeText(LogingAvtivity.this, "账号密码错误!", Toast.LENGTH_SHORT).show();
        }
    }

    void goMain() {
        Toast.makeText(LogingAvtivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LogingAvtivity.this, MainActivity.class);
        startActivity(intent);
        LogingAvtivity.this.finish();
    }
}
