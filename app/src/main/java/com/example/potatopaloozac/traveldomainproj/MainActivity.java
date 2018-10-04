package com.example.potatopaloozac.traveldomainproj;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
<<<<<<< HEAD
import com.anychart.charts.Cartesian;
import com.example.potatopaloozac.traveldomainproj.ui.bus.BusInfoActivity;
import com.example.potatopaloozac.traveldomainproj.ui.city.CityActivity;
import com.example.potatopaloozac.traveldomainproj.ui.coupon.CouponActivity;
import com.example.potatopaloozac.traveldomainproj.ui.gameschedule.GameScheduleActivity;
import com.example.potatopaloozac.traveldomainproj.ui.route.RouteActivity;
import com.example.potatopaloozac.traveldomainproj.ui.seat.SeatInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
=======
import com.anychart.charts.TagCloud;

import java.util.ArrayList;
import java.util.List;
>>>>>>> master

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivityTAG";

<<<<<<< HEAD
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
    @BindView(R.id.bt_gameInfo)
    Button btGameInfo;

=======
    private AnyChartView anyChartView;
>>>>>>> master

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
    }

    @OnClick({R.id.bt_cityInfo, R.id.bt_routeInfo, R.id.bt_busInfo, R.id.bt_seatInfo, R.id.bt_couponInfo, R.id.bt_gameInfo})
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
            case R.id.bt_gameInfo: {
                Intent i = new Intent(MainActivity.this, GameScheduleActivity.class);
                startActivity(i);
                break;
            }
        }
=======
        anyChartView = findViewById(R.id.anyChartView);

        List<DataEntry> dataEntryList = new ArrayList<>();

        dataEntryList.add(new ValueDataEntry("myname0", 123));
        dataEntryList.add(new ValueDataEntry("myname1", 456));
        dataEntryList.add(new ValueDataEntry("myname2", 789));
        dataEntryList.add(new ValueDataEntry("myname3", 12));
        dataEntryList.add(new ValueDataEntry("myname4", 345));

        TagCloud tagCloud = AnyChart.tagCloud();
        tagCloud.data(dataEntryList);
        anyChartView.setChart(tagCloud);
>>>>>>> master
    }


}
