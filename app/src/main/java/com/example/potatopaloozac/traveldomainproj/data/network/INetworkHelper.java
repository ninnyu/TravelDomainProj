package com.example.potatopaloozac.traveldomainproj.data.network;

import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager.*;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public interface INetworkHelper {

    void getCityInfoList(OnCityListener cityListener);
    void getRouteInfo(OnRouteIDListener routeIDListener);
    void getRouteInfo(LatLng city_start, LatLng city_destination, LatLng city_transfer, OnTransferListener listener);
    void getBusInfo(OnBusInfoListener busInfoListener);
    void getSeatInfo(OnSeatInfoListener seatInfoListener);
    void getCouponInfoList(OnCouponListener couponListener);
    void findRoute(String city_start, String city_destination, String city_transfer, IDataManager.OnTransferListener listener);
}
