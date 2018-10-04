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

<<<<<<< HEAD:app/src/main/java/com/example/potatopaloozac/traveldomainproj/ui/city/CityPresenter.java

    public CityPresenter(CityActivity activity) {
        cityView = activity;
=======
    public BookingPresenter(BookingActivity activity) {
        bookingView = activity;
>>>>>>> master:app/src/main/java/com/example/potatopaloozac/traveldomainproj/ui/booking/BookingPresenter.java
        dataManager = new DataManager(activity);
        MySharedPreference.getSharedPreferences(activity);
    }

    @Override
    public void onActivityCreated() {
        dataManager.getCityInfoList(this);
    }

    @Override
    public void getCityList(ArrayList<CityItem> cityList) {
<<<<<<< HEAD:app/src/main/java/com/example/potatopaloozac/traveldomainproj/ui/city/CityPresenter.java
        cityView.showCityList(cityList);
        dataManager.saveCity(cityList);

=======
        bookingView.showCityList(cityList);
>>>>>>> master:app/src/main/java/com/example/potatopaloozac/traveldomainproj/ui/booking/BookingPresenter.java
    }
}
