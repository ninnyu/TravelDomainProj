package com.example.potatopaloozac.traveldomainproj.ui.booking;

import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;

import java.util.ArrayList;

public interface IBookingView {
    void showCityList(ArrayList<CityItem> cityList);
}
