package com.example.potatopaloozac.traveldomainproj.ui.city;

import android.content.SharedPreferences;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.City;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;

import java.util.ArrayList;

public class CityPresenter implements ICityPresenter, IDataManager.OnCityListener {

    ICityView cityView;
    IDataManager dataManager;


    public CityPresenter(CityActivity activity) {
        cityView = activity;
        dataManager = new DataManager(activity);
        MySharedPreference.getSharedPreferences(activity);
    }

    @Override
    public void onActivityCreated() {
        dataManager.getCityInfoList(this);
    }

    @Override
    public void getCityList(ArrayList<CityItem> cityList) {
        cityView.showCityList(cityList);
        dataManager.saveCity(cityList);

    }
}
