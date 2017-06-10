package com.me.movieticket.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.me.movieticket.R;
import com.me.movieticket.database.DataInit;
import com.me.movieticket.views.TabUserView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mTabPager;
    private ImageView mTab1, mTab2, mTab3, mTab4;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB();
        initView();
    }

    private void initDB() {
        Log.i("读取数据库", "正在读取");
        DataInit dataInit = new DataInit(this);
    }

    private void initView() {
        mTabPager = (ViewPager) findViewById(R.id.tabpager);
        mTab1 = (ImageView) findViewById(R.id.img_movie);
        mTab2 = (ImageView) findViewById(R.id.img_other);
        mTab3 = (ImageView) findViewById(R.id.img_disc);
        mTab4 = (ImageView) findViewById(R.id.img_me);

        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));

        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.main_tab_movie, null);
        View view2 = mLi.inflate(R.layout.main_tab_other, null);
        View view3 = mLi.inflate(R.layout.main_tab_discover, null);
        View view4 = mLi.inflate(R.layout.main_tab_me, null);

        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }
        };
        mTabPager.setAdapter(mPagerAdapter);
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mTabPager.setCurrentItem(index);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TabUserView.instance.userResume();
    }
}
