package com.example.potatopaloozac.traveldomainproj.utils;

import com.example.potatopaloozac.traveldomainproj.data.network.model.BusInformation;
import com.example.potatopaloozac.traveldomainproj.data.network.model.City;
import com.example.potatopaloozac.traveldomainproj.data.network.model.Coupon;
import com.example.potatopaloozac.traveldomainproj.data.network.model.Route;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatInformation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /**********  BASE_URL = http://rjtmobile.com/aamir/otr/android-app/  **********/

    //  city = http://rjtmobile.com/aamir/otr/android-app/city.php?
    @GET("city.php")
    Call<City> getCities();

    /**********  routeid for bus search = http://rjtmobile.com/aamir/otr/android-app/
                                    routeinfo.php?
                                    route-startpoint-latitude=41.914196
                                    &route-startpoint-longitude=-88.308685
                                    &route-endpoint-latitude=40.73061
                                    &route-endpoint-longiude=-73.935242
     **********/
    @GET("routeinfo.php")
    Call<Route> getRoute(
            @Query("route-startpoint-latitude") double route_start_lat,
            @Query("route-startpoint-longitude") double route_start_long,
            @Query("route-endpoint-latitude") double route_end_lat,
            @Query("route-endpoint-longiude") double route_end_long);

    /**********  bus details = http://rjtmobile.com/aamir/otr/android-app/businfo.php?routeid=2         **********/
    @GET("businfo.php")
    Call<BusInformation> getBusInfo(@Query("routeid") int routeId);

    /**********  seat information = http://rjtmobile.com/aamir/otr/android-app/seatinfo.php?busid=102   **********/
    @GET("seatinfo.php")
    Call<SeatInformation> getSeatInfo(@Query("busid") int busId);

    /**********  http://rjtmobile.com/aamir/otr/android-app/coupon_list.php?                            **********/
    @GET("coupon_list.php")
    Call<Coupon> getCoupons();
}
