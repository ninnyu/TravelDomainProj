package com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.adapter.BusSeat;
import com.example.potatopaloozac.traveldomainproj.adapter.BusSeatAdapter;
import com.example.potatopaloozac.traveldomainproj.data.network.model.PaymentInfo;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatinformationItem;
import com.example.potatopaloozac.traveldomainproj.ui.booking.passengerdetails.PassengerDetailsActivity;
import com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo.SeatInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeatInfo2Activity extends AppCompatActivity implements ISeatInfoView2 {

    private static final String TAG = "SeatInfo2ActivityTAG";

    private ISeatInfoPresenter2 seatInfoPresenter;
    private List<BusSeat> mylist;
    private RecyclerView recyclerView_seat;
    private BusSeatAdapter myAdapter;
    private static int columns = 5;
    private int seatCount = 0;

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_info2);
        ButterKnife.bind(this);

        activity = this;

        seatInfoPresenter = new SeatInfoPresenter2(this);
        seatInfoPresenter.onActivityCreated();

        recyclerView_seat = findViewById(R.id.recyclerview_seat);

        mylist = new ArrayList<>();

        myAdapter = new BusSeatAdapter(mylist, new BusSeatAdapter.SeatOnClickListener() {
            @Override
            public void onitemclick(BusSeat busSeat) {
                if (busSeat.getType() == 0) {
                    busSeat.setType(2);
                    seatCount++;
                    Log.d(TAG, "onitemclick: " + seatCount);
                } else if (busSeat.getType() == 2) {
                    busSeat.setType(0);
                    seatCount--;
                    Log.d(TAG, "onitemclick: " + seatCount);
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

        String seat_total = seat_info_split[seat_info_split.length - 1];
        String[] seat_total_split = seat_total.split("'");
        int seat_num = Integer.parseInt(seat_total_split[1]);

        for (int i = 1; i <= seat_num; i++) {
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

    @OnClick({R.id.bt_bookSeats})
    public void onViewClicked(View view) {
        if (seatCount > 0) {
            PaymentInfo paymentInfo = getIntent().getParcelableExtra("paymentinfo");
            paymentInfo.setSeatCount_bus1(seatCount);

            Intent i = new Intent(this, SeatInfoActivity.class);
            i.putExtra("paymentinfo", paymentInfo);
            startActivity(i);
        } else
            Toast.makeText(this, "No seat selected", Toast.LENGTH_SHORT).show();
    }
}
