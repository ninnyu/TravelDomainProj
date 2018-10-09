package com.example.potatopaloozac.traveldomainproj.ui.booking.businfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.ui.HomeActivity;
import com.example.potatopaloozac.traveldomainproj.ui.booking.BookingActivity;
import com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo.SeatInfoActivity;
import com.example.potatopaloozac.traveldomainproj.ui.gameschedule.GameScheduleActivity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusInfoActivity extends AppCompatActivity implements IBusInfoView {

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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    LatLng start, destination;
    private IBusInfoPresenter busInfoPresenter;
    private BusinformationItem businformationItem;
    private GoogleMap mMap;

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        activity = this;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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

    @OnClick({R.id.bt_home, R.id.bt_search, R.id.bt_schedule, R.id.bt_bookingChooseSeats})
    public void onViewClicked(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.bt_home:
                i = new Intent(this, HomeActivity.class);
                startActivity(i);
                break;
            case R.id.bt_search:
                i = new Intent(this, BookingActivity.class);
                startActivity(i);
                break;
            case R.id.bt_schedule:
                i = new Intent(this, GameScheduleActivity.class);
                startActivity(i);
                break;
            case R.id.bt_bookingChooseSeats:
                i = new Intent(this, SeatInfoActivity.class);
                i.putExtra("businfo", businformationItem);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        String start_lat = MySharedPreference.readString(MySharedPreference.START_CITY_LAT, "");
        String start_long = MySharedPreference.readString(MySharedPreference.START_CITY_LONG, "");
        String des_lat = MySharedPreference.readString(MySharedPreference.END_CITY_LAT, "");
        String des_long = MySharedPreference.readString(MySharedPreference.END_CITY_LONG, "");
        start = new LatLng(Double.parseDouble(start_lat), Double.parseDouble(start_long));
        destination = new LatLng(Double.parseDouble(des_lat), Double.parseDouble(des_long));

        double mid_lat = (Double.parseDouble(start_lat) + Double.parseDouble(des_lat))/2;
        double mid_long = (Double.parseDouble(start_long) + Double.parseDouble(des_long))/2;
        LatLng mid = new LatLng(mid_lat, mid_long);

        mMap.addMarker(new MarkerOptions().position(start).title("Start"));
        mMap.addMarker(new MarkerOptions().position(destination).title("Destination"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mid,4));

    }
}
