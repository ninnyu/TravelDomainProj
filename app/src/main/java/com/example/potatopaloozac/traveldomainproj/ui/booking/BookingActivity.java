package com.example.potatopaloozac.traveldomainproj.ui.booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;
import com.example.potatopaloozac.traveldomainproj.ui.bus.BusInfoActivity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingActivity extends AppCompatActivity implements IBookingView, AdapterView.OnItemSelectedListener {

    private static final String TAG = "BookingActivityTAG";

    @BindView(R.id.sp_cityFrom)
    Spinner spCityFrom;
    @BindView(R.id.sp_cityGoingTo)
    Spinner spCityGoingTo;
    @BindView(R.id.cv_departure)
    CalendarView cvDeparture;
    @BindView(R.id.bt_bookingSearch)
    Button btBookingSearch;
    @BindView(R.id.bt_home)
    Button btHome;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.bt_schedule)
    Button btSchedule;
    @BindView(R.id.bt_trips)
    Button btTrips;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private IBookingPresenter bookingPresenter;
    ArrayList<CityItem> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        bookingPresenter = new BookingPresenter(this);
        bookingPresenter.onActivityCreated();
    }

    @OnClick({R.id.bt_bookingSearch, R.id.bt_home, R.id.bt_search, R.id.bt_schedule, R.id.bt_trips})
    public void onViewClicked(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.bt_bookingSearch:
                i = new Intent(BookingActivity.this, BusInfoActivity.class);
                startActivity(i);
                break;
            case R.id.bt_home:
                break;
            case R.id.bt_search:
                break;
            case R.id.bt_schedule:
                break;
            case R.id.bt_trips:
                break;
        }
    }

    @Override
    public void showCityList(ArrayList<CityItem> cityList) {
        this.cityList = cityList;

        String[] s = new String[cityList.size()];

        for (int i = 0; i < cityList.size(); i++) {
            s[i] = cityList.get(i).getCityname();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCityFrom.setAdapter(adapter);
        spCityFrom.setOnItemSelectedListener(this);

        spCityGoingTo.setAdapter(adapter);
        spCityGoingTo.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s = parent.getItemAtPosition(position).toString();

        switch (parent.getId()) {
            case R.id.sp_cityFrom: {
                MySharedPreference.writeString(MySharedPreference.START_CITY_NAME, s);
                MySharedPreference.writeString(MySharedPreference.START_CITY_LAT, cityList.get(position).getCitylatitude());
                MySharedPreference.writeString(MySharedPreference.START_CITY_LONG, cityList.get(position).getCitylongtitude());
                break;
            }
            case R.id.sp_cityGoingTo: {
                MySharedPreference.writeString(MySharedPreference.END_CITY_NAME, s);
                MySharedPreference.writeString(MySharedPreference.END_CITY_LAT, cityList.get(position).getCitylatitude());
                MySharedPreference.writeString(MySharedPreference.END_CITY_LONG, cityList.get(position).getCitylongtitude());
                break;
            }
        }

        Log.d(TAG, "onItemSelected: " + MySharedPreference.readString(MySharedPreference.START_CITY_NAME, "")
                + " " + MySharedPreference.readString(MySharedPreference.END_CITY_NAME, ""));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
