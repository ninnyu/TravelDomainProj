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
import com.anychart.charts.TagCloud;

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

        dataEntryList.add(new ValueDataEntry("myname0", 123));
        dataEntryList.add(new ValueDataEntry("myname1", 456));
        dataEntryList.add(new ValueDataEntry("myname2", 789));
        dataEntryList.add(new ValueDataEntry("myname3", 12));
        dataEntryList.add(new ValueDataEntry("myname4", 345));

        TagCloud tagCloud = AnyChart.tagCloud();
        tagCloud.data(dataEntryList);
        anyChartView.setChart(tagCloud);
    }
}
