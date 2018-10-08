package com.example.potatopaloozac.traveldomainproj.ui.booking;

import android.content.SharedPreferences;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.City;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;

import java.util.ArrayList;

public class BookingPresenter implements IBookingPresenter, IDataManager.OnCityListener {

    IBookingView bookingView;
    IDataManager dataManager;

    public BookingPresenter(BookingActivity activity) {
        bookingView = activity;
        dataManager = new DataManager(activity);
    }

    @Override
    public void onActivityCreated() {
        dataManager.getCityInfoList(this);
    }

    @Override
    public void getCityList(ArrayList<CityItem> cityList) {
        dataManager.saveCity(cityList);
        bookingView.showCityList(cityList);
    }
}
