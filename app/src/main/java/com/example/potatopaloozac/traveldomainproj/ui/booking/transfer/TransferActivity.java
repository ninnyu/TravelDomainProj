package com.example.potatopaloozac.traveldomainproj.ui.booking.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.PaymentInfo;
import com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo.SeatInfoActivity;
import com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo2.SeatInfo2Activity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferActivity extends AppCompatActivity implements IViewTransfer, OnMapReadyCallback {

    private static final String TAG = "TransferActivityTAG";

    @BindView(R.id.tv_transfer_bus1_leaving)
    TextView tvTransferBus1Leaving;
    @BindView(R.id.tv_transfer_bus1_going)
    TextView tvTransferBus1Going;
    @BindView(R.id.tv_transfer_bus1_departure)
    TextView tvTransferBus1Departure;
    @BindView(R.id.tv_transfer_bus1_bustype)
    TextView tvTransferBus1Bustype;
    @BindView(R.id.tv_transfer_bus1_duration)
    TextView tvTransferBus1Duration;
    @BindView(R.id.tv_transfer_bus1_departureTime)
    TextView tvTransferBus1DepartureTime;
    @BindView(R.id.tv_transfer_bus1_arrival)
    TextView tvTransferBus1Arrival;
    @BindView(R.id.tv_transfer_bus1_fare)
    TextView tvTransferBus1Fare;

    @BindView(R.id.tv_transfer_bus2_leaving)
    TextView tvTransferBus2Leaving;
    @BindView(R.id.tv_transfer_bus2_going)
    TextView tvTransferBus2Going;
    @BindView(R.id.tv_transfer_bus2_departure)
    TextView tvTransferBus2Departure;
    @BindView(R.id.tv_transfer_bus2_bustype)
    TextView tvTransferBus2Bustype;
    @BindView(R.id.tv_transfer_bus2_duration)
    TextView tvTransferBus2Duration;
    @BindView(R.id.tv_transfer_bus2_departureTime)
    TextView tvTransferBus2DepartureTime;
    @BindView(R.id.tv_transfer_bus2_arrival)
    TextView tvTransferBus2Arrival;
    @BindView(R.id.tv_transfer_bus2_fare)
    TextView tvTransferBus2Fare;

    private String city_start, city_destination;
    private IPresenterTransfer presenter;
    private PaymentInfo paymentInfo;

    Map<String, Boolean> map_start_transfer, map_transfer_destination;
    double city_lat, city_long;

    private GoogleMap mMap;
    Polyline polyline;
    LatLng start, destination, transfer;
    int idx = 0;
    List<String> msg_list;

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);

        activity = this;

        paymentInfo = getIntent().getParcelableExtra("paymentinfo");

        Log.d(TAG, "onCreate: " + paymentInfo.toString());

        tvTransferBus1Departure.setText("Sorry, no transfer available");
        tvTransferBus2Departure.setText("Sorry, no transfer available");

        msg_list = new ArrayList<>();
        map_start_transfer = new HashMap<>();
        map_transfer_destination = new HashMap<>();

        city_start = MySharedPreference.readString(MySharedPreference.START_CITY_NAME, "");
        city_destination = MySharedPreference.readString(MySharedPreference.END_CITY_NAME, "");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        presenter = new PresenterTransfer(this);
        presenter.findTransfer(city_start, city_destination);
    }


    @Override
    public void showTransfer(String city_nm) {
        paymentInfo.setTransferCity(new CityItem());
        paymentInfo.getTransferCity().setCityname(city_nm);

        tvTransferBus1Leaving.setText(city_start);
        tvTransferBus2Leaving.setText(city_nm);

        tvTransferBus1Going.setText(city_nm);
        tvTransferBus2Going.setText(city_destination);

        presenter.getCityInfo(city_nm);
        transfer = new LatLng(city_lat, city_long);

        presenter.getRouteInfo(start, destination, transfer);

        mMap.addMarker(new MarkerOptions().position(transfer).title("Transfer"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(transfer, 4));

        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        start,
                        transfer,
                        destination
                ));

    }

    @Override
    public void setCityInfo(String city_info) {
        String[] city_info_split = city_info.split(" ");
        city_lat = Double.parseDouble(city_info_split[0]);
        city_long = Double.parseDouble(city_info_split[1]);
    }

    @Override
    public void showStartTransRoute(String info) {
        Log.d("MyRouteInfo", info);
    }

    @Override
    public void showTransDesRoute(String info) {
        Log.d("MyRouteInfo", info);
    }

    @Override
    public void setBusInfoStartTrans(BusinformationItem busInfo) {
        paymentInfo.setBusinfo1(busInfo);

        tvTransferBus1Departure.setText(MySharedPreference.readString(MySharedPreference.DEPARTURE_DATE, ""));
        tvTransferBus1Bustype.setText(busInfo.getBustype());
        tvTransferBus1Duration.setText(busInfo.getJournyduration());
        tvTransferBus1DepartureTime.setText(busInfo.getBusdeparturetime());
        tvTransferBus1Arrival.setText(busInfo.getDropingtime());
        tvTransferBus1Fare.setText(busInfo.getFare());
    }

    @Override
    public void setBusInfoTransDes(BusinformationItem busInfo) {
        paymentInfo.setBusinfo2(busInfo);

        tvTransferBus2Departure.setText(MySharedPreference.readString(MySharedPreference.DEPARTURE_DATE, ""));
        tvTransferBus2Bustype.setText(busInfo.getBustype());
        tvTransferBus2Duration.setText(busInfo.getJournyduration());
        tvTransferBus2DepartureTime.setText(busInfo.getBusdeparturetime());
        tvTransferBus2Arrival.setText(busInfo.getDropingtime());
        tvTransferBus2Fare.setText(busInfo.getFare());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.d("MyMap", city_start);
        presenter.getCityInfo(city_start);
        start = new LatLng(city_lat, city_long);
        mMap.addMarker(new MarkerOptions().position(start).title("Start"));

        presenter.getCityInfo(city_destination);
        destination = new LatLng(city_lat, city_long);
        mMap.addMarker(new MarkerOptions().position(destination).title("Destination"));
    }

    @OnClick(R.id.bt_transferChooseSeats)
    public void onViewClicked() {
        MySharedPreference.writeBoolean(MySharedPreference.SEATSELECTED_BUS1, false);
        Intent i = new Intent(this, SeatInfo2Activity.class);
        i.putExtra("paymentinfo", paymentInfo);
        startActivity(i);
    }
}
