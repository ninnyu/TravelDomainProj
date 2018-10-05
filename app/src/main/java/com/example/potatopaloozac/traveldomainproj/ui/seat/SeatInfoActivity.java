package com.example.potatopaloozac.traveldomainproj.ui.seat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.BaseActivity;
import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.adapter.BusSeat;
import com.example.potatopaloozac.traveldomainproj.adapter.BusSeatAdapter;
import com.example.potatopaloozac.traveldomainproj.adapter.GameSchedule;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatinformationItem;
import com.example.potatopaloozac.traveldomainproj.ui.gameschedule.GameScheduleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeatInfoActivity extends BaseActivity implements ISeatInfoView {

    private static final String TAG = "SeatInfoActivityTAG";

    TextView tv_seatInfo;
    ISeatInfoPresenter seatInfoPresenter;
    List<BusSeat> mylist;
    RecyclerView recyclerView_seat;
    BusSeatAdapter myAdapter;
    static int columns = 5;
    @BindView(R.id.bt_schedule)
    Button btSchedule;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_info);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        seatInfoPresenter = new SeatInfoPresenter(this);
        seatInfoPresenter.onActivityCreated();

        recyclerView_seat = findViewById(R.id.recyclerview_seat);

        mylist = new ArrayList<>();

        myAdapter = new BusSeatAdapter(mylist, new BusSeatAdapter.SeatOnClickListener() {
            @Override
            public void onitemclick(BusSeat busSeat) {
                if (busSeat.getType() == 0) {
                    busSeat.setType(2);
                } else if (busSeat.getType() == 2) {
                    busSeat.setType(0);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        GridLayoutManager manager = new GridLayoutManager(this, columns);
        recyclerView_seat.setLayoutManager(manager);
        recyclerView_seat.setAdapter(myAdapter);

    }

    @Override
    public void showSeatInfo(SeatinformationItem seatinformationItem) {
        String seat_info = seatinformationItem.toString();
        Log.d("MySeat", seat_info);

        String[] seat_info_split = seat_info.split(",");

        //totalseat='47'
        //get the total number of seats
        String seat_total = seat_info_split[seat_info_split.length - 1];
        //Log.d("MySeatInfo", seat_total);
        String[] seat_total_split = seat_total.split("'");
        int seat_num = Integer.parseInt(seat_total_split[1]);

        for (int i = 1; i <= seat_num; i++) {
            //Log.d("MySeat", seat_info_split[i]);
            //s40='0'
            String[] seat_avail_split = seat_info_split[i].split("'");
            int seat_type = Integer.parseInt(seat_avail_split[1]);
            BusSeat busSeat = new BusSeat(seat_type, i);
            if (i % 4 == 3) {
                mylist.add(new BusSeat(3, 0));
            }
            mylist.add(busSeat);
            myAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.bt_schedule)
    public void onViewClicked() {
        Bundle b = getIntent().getExtras();
        Intent i = new Intent(this, GameScheduleActivity.class);
        i.putExtras(b);
        startActivity(i);
    }
}
