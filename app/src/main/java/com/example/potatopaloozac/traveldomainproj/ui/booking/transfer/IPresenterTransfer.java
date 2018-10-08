package com.example.potatopaloozac.traveldomainproj.ui.booking.transfer;

import com.google.android.gms.maps.model.LatLng;

public interface IPresenterTransfer {

    void findTransfer(String city_start, String city_destination);
    void getCityInfo(String city_nm);
    void getRouteInfo(LatLng city_start, LatLng city_destination, LatLng city_transfer);
}
