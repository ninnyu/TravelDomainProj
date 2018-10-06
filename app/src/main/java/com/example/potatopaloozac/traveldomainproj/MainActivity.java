package com.example.potatopaloozac.traveldomainproj;

import android.os.Bundle;
import android.widget.Button;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.anychart.charts.Cartesian;
import com.anychart.charts.CircularGauge;
import com.anychart.charts.Pie;
import com.anychart.charts.TagCloud;
import com.anychart.core.gauge.pointers.Bar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivityTAG";

    private AnyChartView anyChartView;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anyChartView = findViewById(R.id.anyChartView);

        List<DataEntry> dataEntryList = new ArrayList<>();

        dataEntryList.add(new ValueDataEntry("Atlanta", 51000));
        dataEntryList.add(new ValueDataEntry("Philadelphia", 43000));

        dataEntryList.add(new ValueDataEntry("New York City", 33000));
        dataEntryList.add(new ValueDataEntry("Dallas", 25000));

        dataEntryList.add(new ValueDataEntry("Washington", 20000));

        dataEntryList.add(new ValueDataEntry("Denver", 14000));
        dataEntryList.add(new ValueDataEntry("st Charles", 25));
        //st charles, 25000
        //new york city, 33 million
        //dallas, 25 million
        //atlanta, 51 million
        //washington, 20 million
        //Philadelphia, 43 million
        //Denver, 14 million


//        Cartesian cartesian = AnyChart.cartesian();
//        cartesian.data(dataEntryList);
//        anyChartView.setChart(cartesian);

//        Pie pie = AnyChart.pie();
//        pie.data(dataEntryList);
//        anyChartView.setChart(pie);

        Cartesian bar = AnyChart.bar();
        bar.data(dataEntryList);
        bar.title().enabled();
        bar.title("Most Popular Destinations");

        anyChartView.setChart(bar);


    }
}
