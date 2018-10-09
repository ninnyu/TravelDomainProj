package com.example.potatopaloozac.traveldomainproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.example.potatopaloozac.traveldomainproj.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG";

    TextView textView;
    Button button;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_techsUsed);
        button = findViewById(R.id.bt_startDemo);

        String s = "- Retrofit2\n" +
                "- RxJava (validation)\n" +
                "- RecyclerView with CardView\n" +
                "- ProgressDialog\n" +
                "- AlertDialog\n" +
                "- Google Maps\n" +
                "- AnyChart\n" +
                "- SQLite Database\n" +
                "- MVP Design Pattern\n" +
                "- Singleton Design Pattern\n" +
                "- Shared Preferences\n" +
                "- Single Sign-On using Firebase\n" +
                "- Parcelable\n" +
                "- PayPal\n" +
                "- QRCode\n" +
                "";

        textView.setText(s);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
