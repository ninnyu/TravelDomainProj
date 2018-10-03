package com.example.potatopaloozac.traveldomainproj.data;

import android.content.Context;

import com.example.potatopaloozac.traveldomainproj.data.network.INetworkHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.NetworkHelper;

public class DataManager implements IDataManager {

    private INetworkHelper networkHelper;

    public DataManager(Context context) {
        networkHelper = new NetworkHelper(context);
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
}
