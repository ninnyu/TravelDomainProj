package com.example.potatopaloozac.traveldomainproj.ui.booking;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;

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
        bookingView.showCityList(cityList);
    }
}
