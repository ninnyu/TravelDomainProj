package com.example.potatopaloozac.traveldomainproj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.ui.booking.BookingActivity;
import com.example.potatopaloozac.traveldomainproj.ui.gameschedule.GameScheduleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bt_home)
    Button btHome;

    private AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        btHome.setBackground(getResources().getDrawable(R.drawable.ic_toolbar_home_blue_24dp));
        anyChartView = findViewById(R.id.anyChartView);

        List<DataEntry> dataEntryList = new ArrayList<>();

        dataEntryList.add(new ValueDataEntry("NYC, NY", 60.5));
        dataEntryList.add(new ValueDataEntry("Dallas, TX", 44));
        dataEntryList.add(new ValueDataEntry("Atlanta, GA", 42));
        dataEntryList.add(new ValueDataEntry("Philadelphia, PA", 41));
        dataEntryList.add(new ValueDataEntry("Washington, DC", 20));
        dataEntryList.add(new ValueDataEntry("Denver, CO", 17.4));
        dataEntryList.add(new ValueDataEntry("St Charles, IL", 51000 / 1000000));

        Cartesian cartesian = AnyChart.cartesian();
        Column column = cartesian.column(dataEntryList);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: } million");

        cartesian.animation(true);
        cartesian.title("Most Popular Destinations");

        cartesian.yScale().minimum(0d);
        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Cities");
        cartesian.yAxis(0).title("Visitors (Millions)");

        anyChartView.setChart(cartesian);
    }

    @OnClick({R.id.bt_home, R.id.bt_search, R.id.bt_schedule})
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
                i = new Intent(this, GameScheduleActivity.class);
                startActivity(i);
                break;
        }
    }
}
