package com.example.potatopaloozac.traveldomainproj.data;

import android.content.Context;

import com.example.potatopaloozac.traveldomainproj.data.database.DBHelper;
import com.example.potatopaloozac.traveldomainproj.data.database.IDBHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.INetworkHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.NetworkHelper;

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
    public void getBusInfo(OnBusInfoListener busInfoListener) {
        networkHelper.getBusInfo(busInfoListener);
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
    public void loadDataBase() {

    }

    @Override
    public List<String> findGame(String busdeparturetime, String journyduration, IDataManager.OnGameScheduleListener listener) {
        return dbhelper.findGame(busdeparturetime, journyduration, listener);
    }
}
