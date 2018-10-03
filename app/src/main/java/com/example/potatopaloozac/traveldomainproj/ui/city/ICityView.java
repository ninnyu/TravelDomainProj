package com.example.potatopaloozac.traveldomainproj.ui.city;

import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;

import java.util.ArrayList;

public interface ICityView {
    void showCityList(ArrayList<CityItem> cityList);
}
