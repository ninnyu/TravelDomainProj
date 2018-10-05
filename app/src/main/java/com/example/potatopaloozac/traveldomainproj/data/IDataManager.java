package com.example.potatopaloozac.traveldomainproj.data;

import com.example.potatopaloozac.traveldomainproj.data.database.IDBHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.INetworkHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CouponsItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.RouteItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatinformationItem;

import java.util.ArrayList;

public interface IDataManager extends INetworkHelper, IDBHelper {

    interface OnCityListener {
        void getCityList(ArrayList<CityItem> cityList);
    }

    interface OnRouteIDListener {
        void getRouteID(RouteItem routeItem);
    }

    interface OnBusInfoListener {
        void getBusDetails(BusinformationItem businformationItem);
    }

    interface OnSeatInfoListener {
        void getSeatDetails(SeatinformationItem seatinformationItem);
    }

    interface OnCouponListener {
        void getCouponList(ArrayList<CouponsItem> couponList);
    }

    interface OnGameScheduleListener{
        void updateGame(String game_info);
    }

    interface OnTransferListener{
        boolean findRoute(String city_start, String city_destination, String city_transfer);
        void addStartTransfer(String city_nm, boolean flag);
        void addTransferDestination(String city_nm, boolean flag);
    }

}
