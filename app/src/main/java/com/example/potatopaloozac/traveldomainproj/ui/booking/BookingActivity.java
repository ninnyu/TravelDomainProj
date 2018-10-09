package com.example.potatopaloozac.traveldomainproj.ui.booking;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.potatopaloozac.traveldomainproj.data.network.model.PaymentInfo;
import com.example.potatopaloozac.traveldomainproj.ui.HomeActivity;
import com.example.potatopaloozac.traveldomainproj.ui.booking.businfo.BusInfoActivity;
import com.example.potatopaloozac.traveldomainproj.ui.gameschedule.GameScheduleActivity;
import com.example.potatopaloozac.traveldomainproj.ui.login.LoginActivity;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private IBookingPresenter bookingPresenter;
    private ArrayList<CityItem> cityList;
    private CityItem cityStart, cityEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        bookingPresenter = new BookingPresenter(this);
        bookingPresenter.onActivityCreated();

        MySharedPreference.writeString(MySharedPreference.DEPARTURE_DATE, "Today");

        cvDeparture.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String s = (month + 1) + "-" + dayOfMonth + "-" + year;
                MySharedPreference.writeString(MySharedPreference.DEPARTURE_DATE, s);
            }
        });
    }

    @OnClick({R.id.bt_bookingSearch, R.id.bt_home, R.id.bt_search, R.id.bt_schedule})
    public void onViewClicked(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.bt_bookingSearch:
                if (cityStart.getCityname().equals(cityEnd.getCityname())) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setTitle(R.string.sameCityTitle);
                    alertDialogBuilder.setMessage(R.string.sameCityMessage);

                    alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialogBuilder.show();
                } else {
                    PaymentInfo paymentInfo = new PaymentInfo();
                    paymentInfo.setStartCity(cityStart);
                    paymentInfo.setEndCity(cityEnd);

                    i = new Intent(this, BusInfoActivity.class);
                    i.putExtra("paymentinfo", paymentInfo);
                    startActivity(i);
                }
                break;
            case R.id.bt_home:
                i = new Intent(this, HomeActivity.class);
                startActivity(i);
                break;
            case R.id.bt_search:
                break;
            case R.id.bt_schedule:
                i = new Intent(this, GameScheduleActivity.class);
                startActivity(i);
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
                cityStart = cityList.get(position);
                MySharedPreference.writeString(MySharedPreference.START_CITY_NAME, s);
                MySharedPreference.writeString(MySharedPreference.START_CITY_LAT, cityList.get(position).getCitylatitude());
                MySharedPreference.writeString(MySharedPreference.START_CITY_LONG, cityList.get(position).getCitylongtitude());
                break;
            }
            case R.id.sp_cityGoingTo: {
                cityEnd = cityList.get(position);
                MySharedPreference.writeString(MySharedPreference.END_CITY_NAME, s);
                MySharedPreference.writeString(MySharedPreference.END_CITY_LAT, cityList.get(position).getCitylatitude());
                MySharedPreference.writeString(MySharedPreference.END_CITY_LONG, cityList.get(position).getCitylongtitude());
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
