package com.example.potatopaloozac.traveldomainproj.data.network;

import com.example.potatopaloozac.traveldomainproj.data.IDataManager.*;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;

import java.util.ArrayList;

public interface INetworkHelper {

    void getCityInfoList(OnCityListener cityListener);
    void getRouteInfo(CityItem start, CityItem destination, OnRouteIDListener routeIDListener);
    void getBusInfo(OnBusInfoListener busInfoListener);
    void getSeatInfo(OnSeatInfoListener seatInfoListener);
    void getCouponInfoList(OnCouponListener couponListener);
}
