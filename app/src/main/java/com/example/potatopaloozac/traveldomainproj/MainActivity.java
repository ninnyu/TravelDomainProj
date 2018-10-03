package com.example.potatopaloozac.traveldomainproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.potatopaloozac.traveldomainproj.data.network.model.Coupon;
import com.example.potatopaloozac.traveldomainproj.ui.bus.BusInfoActivity;
import com.example.potatopaloozac.traveldomainproj.ui.city.CityActivity;
import com.example.potatopaloozac.traveldomainproj.ui.coupon.CouponActivity;
import com.example.potatopaloozac.traveldomainproj.ui.route.RouteActivity;
import com.example.potatopaloozac.traveldomainproj.ui.seat.SeatInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.bt_cityInfo)
    Button btCityInfo;
    @BindView(R.id.bt_routeInfo)
    Button btRouteInfo;
    @BindView(R.id.bt_busInfo)
    Button btBusInfo;
    @BindView(R.id.bt_seatInfo)
    Button btSeatInfo;
    @BindView(R.id.bt_couponInfo)
    Button btCouponInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.bt_cityInfo, R.id.bt_routeInfo, R.id.bt_busInfo, R.id.bt_seatInfo, R.id.bt_couponInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cityInfo: {
                Intent i = new Intent(MainActivity.this, CityActivity.class);
                startActivity(i);
                break;
            }
            case R.id.bt_routeInfo: {
                Intent i = new Intent(MainActivity.this, RouteActivity.class);
                startActivity(i);
                break;
            }
            case R.id.bt_busInfo: {
                Intent i = new Intent(MainActivity.this, BusInfoActivity.class);
                startActivity(i);
                break;
            }
            case R.id.bt_seatInfo: {
                Intent i = new Intent(MainActivity.this, SeatInfoActivity.class);
                startActivity(i);
                break;
            }
            case R.id.bt_couponInfo: {
                Intent i = new Intent(MainActivity.this, CouponActivity.class);
                startActivity(i);
                break;
            }
        }
    }
}
