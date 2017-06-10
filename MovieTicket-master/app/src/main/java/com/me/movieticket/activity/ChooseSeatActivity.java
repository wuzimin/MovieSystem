package com.me.movieticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.adapter.AirplaneAdapter;
import com.me.movieticket.database.DataOperate;
import com.me.movieticket.entity.AbstractItem;
import com.me.movieticket.entity.CenterItem;
import com.me.movieticket.entity.EdgeItem;
import com.me.movieticket.entity.EmptyItem;
import com.me.movieticket.entity.MovieInfo;
import com.me.movieticket.entity.TicketItem;
import com.me.movieticket.interfaces.OnSeatSelected;

import java.util.ArrayList;
import java.util.List;

public class ChooseSeatActivity extends Activity implements OnSeatSelected {

    private static final int COLUMNS = 5;
    private int seatsAmount;
    private ImageButton btn_sure_seat;
    private TextView tv_seat_selected;
    public List<Integer> seat_lists;

    List<Integer> last_seat;

    private ImageButton ib_back;

    private TicketItem ticketItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seat);
        //接受电影票信息
        Intent intent = this.getIntent();
        ticketItem = (TicketItem)intent.getSerializableExtra("ticketItem");
        init();

    }

    private void init() {
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseSeatActivity.this.finish();
            }
        });
        seatsAmount = 30;
        seat_lists = null;

        btn_sure_seat = (ImageButton) findViewById(R.id.btn_sure_seat);
        tv_seat_selected = (TextView) findViewById(R.id.tv_seat_selected);
        btn_sure_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seat_lists == null || seat_lists.size() == 0) {
                    Toast.makeText(ChooseSeatActivity.this, "请选择座位!", Toast.LENGTH_SHORT).show();
                } else {
                    MovieInfoActivity.instance.finish();
                    //更新已经选的座位
                    DataOperate db =  new DataOperate();
                    db.updateSeat(ticketItem.get_id(), seat_lists, last_seat);

                    Intent intent = new Intent(ChooseSeatActivity.this, OrderInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ticket", ticketItem);
                    bundle.putIntegerArrayList("cur_order", (ArrayList<Integer>)seat_lists);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    ChooseSeatActivity.this.finish();
                }
            }
        });

        last_seat = getHasOrderedSeat();
        List<AbstractItem> items = new ArrayList<>();
        for (int i=0; i<seatsAmount; i++) {

            if (i%COLUMNS==0 || i%COLUMNS==4) {
                if (isSeatSelected(last_seat, i))
                    items.add(new EdgeItem(String.valueOf(i), true));
                else
                    items.add(new EdgeItem(String.valueOf(i), false));
            } else if (i%COLUMNS==1 || i%COLUMNS==3) {
                if (isSeatSelected(last_seat, i))
                    items.add(new CenterItem(String.valueOf(i), true));
                else
                    items.add(new CenterItem(String.valueOf(i), false));
            } else {
                items.add(new EmptyItem(String.valueOf(i), false));
            }
        }

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        AirplaneAdapter adapter = new AirplaneAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSeatSelected(int count, List<Integer> items) {
        seat_lists = items;
        tv_seat_selected.setText("已选座位: " + items.toString());
    }

    private List<Integer> getHasOrderedSeat() {
        List<Integer> list = new ArrayList<>();
        list = ticketItem.getOrder_list();
        return list;
    }

    private boolean isSeatSelected(List<Integer> list, int num) {
        if (list == null)
            return false;
        for (int i : list) {
            if (i == num)
                return true;
        }
        return false;
    }
}
