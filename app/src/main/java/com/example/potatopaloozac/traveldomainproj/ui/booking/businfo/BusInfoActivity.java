package com.example.potatopaloozac.traveldomainproj.ui.booking.businfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.BaseActivity;
import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.ui.booking.BookingActivity;
import com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo.SeatInfoActivity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusInfoActivity extends BaseActivity implements IBusInfoView {

    @BindView(R.id.tv_busInfo_leavingFrom)
    TextView tvBusInfoLeavingFrom;
    @BindView(R.id.tv_busInfo_goingTo)
    TextView tvBusInfoGoingTo;
    @BindView(R.id.tv_busInfo_departureDate)
    TextView tvBusInfoDepartureDate;
    @BindView(R.id.tv_busInfo_busType)
    TextView tvBusInfoBusType;
    @BindView(R.id.tv_busInfo_duration)
    TextView tvBusInfoDuration;
    @BindView(R.id.tv_busInfo_departureTime)
    TextView tvBusInfoDepartureTime;
    @BindView(R.id.tv_busInfo_droppingTime)
    TextView tvBusInfoDroppingTime;
    @BindView(R.id.tv_busInfo_fare)
    TextView tvBusInfoFare;
    @BindView(R.id.bt_home)
    Button btHome;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.bt_schedule)
    Button btSchedule;
    @BindView(R.id.bt_trips)
    Button btTrips;
    @BindView(R.id.bt_bookingChooseSeats)
    Button btBookingChooseSeats;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private IBusInfoPresenter busInfoPresenter;
    private BusinformationItem businformationItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        busInfoPresenter = new BusInfoPresenter(this);
        busInfoPresenter.onActivityCreated();
    }

    @Override
    public void showBusDetails(BusinformationItem businformationItem) {
        this.businformationItem = businformationItem;

        String from = MySharedPreference.readString(MySharedPreference.START_CITY_NAME, "");
        String going = MySharedPreference.readString(MySharedPreference.END_CITY_NAME, "");
        String date = MySharedPreference.readString(MySharedPreference.DEPARTURE_DATE, "Today");

        tvBusInfoLeavingFrom.setText(from);
        tvBusInfoGoingTo.setText(going);
        tvBusInfoDepartureDate.setText(date);
        tvBusInfoBusType.setText(businformationItem.getBustype());
        tvBusInfoDuration.setText(businformationItem.getJournyduration());
        tvBusInfoDepartureTime.setText(businformationItem.getBusdeparturetime());
        tvBusInfoDroppingTime.setText(businformationItem.getDropingtime());
        tvBusInfoFare.setText(businformationItem.getFare());
    }

    @OnClick({R.id.bt_home, R.id.bt_search, R.id.bt_schedule, R.id.bt_trips, R.id.bt_bookingChooseSeats})
    public void onViewClicked(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.bt_home:
                break;
            case R.id.bt_search:
                i = new Intent(this, BookingActivity.class);
                startActivity(i);
                break;
            case R.id.bt_schedule:
                break;
            case R.id.bt_trips:
                break;
            case R.id.bt_bookingChooseSeats:
                i = new Intent(this, SeatInfoActivity.class);
                Bundle b = new Bundle();
                b.putString("bus_departure", businformationItem.getBusdeparturetime());
                b.putString("bus_duration", businformationItem.getJournyduration());
                i.putExtras(b);
                startActivity(i);
                break;
        }
    }
}