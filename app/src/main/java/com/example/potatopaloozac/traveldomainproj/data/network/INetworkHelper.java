package com.example.potatopaloozac.traveldomainproj.data.network;

import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager.*;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;

import java.util.ArrayList;

public interface INetworkHelper {

    void getCityInfoList(OnCityListener cityListener);
    void getRouteInfo(OnRouteIDListener routeIDListener);
    void getBusInfo(OnBusInfoListener busInfoListener);
    void getSeatInfo(OnSeatInfoListener seatInfoListener);
    void getCouponInfoList(OnCouponListener couponListener);
    boolean findRoute(String city_start, String city_destination, String city_transfer, IDataManager.OnTransferListener listener);
}
