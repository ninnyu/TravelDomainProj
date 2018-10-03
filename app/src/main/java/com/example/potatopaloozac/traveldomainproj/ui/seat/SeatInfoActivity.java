package com.example.potatopaloozac.traveldomainproj.ui.seat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatinformationItem;

public class SeatInfoActivity extends AppCompatActivity implements ISeatInfoView {

    TextView tv_seatInfo;

    ISeatInfoPresenter seatInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_info);

        tv_seatInfo = findViewById(R.id.tv_seatInfo);

        seatInfoPresenter = new SeatInfoPresenter(this);
        seatInfoPresenter.onActivityCreated();
    }

    @Override
    public void showSeatInfo(SeatinformationItem seatinformationItem) {

        String s = seatinformationItem.toString();

        tv_seatInfo.setText(s);
    }
}
