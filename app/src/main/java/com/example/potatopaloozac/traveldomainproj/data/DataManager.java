package com.example.potatopaloozac.traveldomainproj.data;

import android.content.Context;

import com.example.potatopaloozac.traveldomainproj.data.database.DBHelper;
import com.example.potatopaloozac.traveldomainproj.data.database.IDBHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.INetworkHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.NetworkHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class DataManager implements IDataManager {

    private INetworkHelper networkHelper;
    private IDBHelper dbhelper;

    public DataManager(Context context) {
        networkHelper = new NetworkHelper(context);
        dbhelper = new DBHelper(context);
        dbhelper.loadDataBase();
    }

    @Override
    public void getCityInfoList(OnCityListener cityListener) {
        networkHelper.getCityInfoList(cityListener);
    }

    @Override
    public void getRouteInfo(OnRouteIDListener routeIDListener) {
        networkHelper.getRouteInfo(routeIDListener);
    }

    @Override
    public void getRouteInfo(LatLng city_start, LatLng city_destination, LatLng city_transfer, OnTransferListener listener) {
        networkHelper.getRouteInfo(city_start, city_destination, city_transfer, listener);
    }

    @Override
    public void getBusInfo(OnBusInfoListener busInfoListener) {
        networkHelper.getBusInfo(busInfoListener);
    }

    @Override
    public void getBusInfo(String routeid, OnTransferListener listener) {
        networkHelper.getBusInfo(routeid, listener);
    }

    @Override
    public void getSeatInfo(OnSeatInfoListener seatInfoListener) {
        networkHelper.getSeatInfo(seatInfoListener);
    }

    @Override
    public void getCouponInfoList(OnCouponListener couponListener) {
        networkHelper.getCouponInfoList(couponListener);
    }

    @Override
    public void findRoute(String city_start, String city_destination, String city_transfer, OnTransferListener listener) {
        networkHelper.findRoute(city_start, city_destination, city_transfer, listener);

    }


    @Override
    public void loadDataBase() {

    }

    @Override
    public void findGame(String busdeparturetime, String journyduration, IDataManager.OnGameScheduleListener listener) {
        dbhelper.findGame(busdeparturetime, journyduration, listener);
    }

    @Override
    public void saveCity(List<CityItem> cityList) {
        dbhelper.saveCity(cityList);
    }

    @Override
    public void findTransfer(String city_start, String city_destination, OnTransferListener listener) {
        dbhelper.findTransfer(city_start, city_destination, listener);
    }

    @Override
    public void getCityInfo(String city_nm, OnTransferListener listener) {
        dbhelper.getCityInfo(city_nm, listener);
    }


}
