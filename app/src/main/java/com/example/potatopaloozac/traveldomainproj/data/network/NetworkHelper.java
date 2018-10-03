package com.example.potatopaloozac.traveldomainproj.data.network;

import android.content.Context;
import android.util.Log;

import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusInformation;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.City;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.Coupon;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CouponsItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.Route;
import com.example.potatopaloozac.traveldomainproj.data.network.model.RouteItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatInformation;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatinformationItem;
import com.example.potatopaloozac.traveldomainproj.utils.ApiService;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;
import com.example.potatopaloozac.traveldomainproj.utils.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkHelper implements INetworkHelper {

    private static final String TAG = "NetworkHelper";

    private ArrayList<CityItem> cityList;
    private RouteItem routeItem;
    private BusinformationItem businformationItem;
    private SeatinformationItem seatinformationItem;
    private ArrayList<CouponsItem> couponList;

    private Context context;
    private ApiService apiService;

    public NetworkHelper(Context context) {
        this.context = context;
        this.apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        MySharedPreference.getSharedPreferences(context);
    }

    @Override
    public void getCityInfoList(final IDataManager.OnCityListener cityListener) {

        Call<City> cityCall = apiService.getCities();
        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                Log.d(TAG, "onResponse: " + response.body());

                cityList = new ArrayList<>(response.body().getCity());
                cityListener.getCityList(cityList);
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                cityListener.getCityList(cityList);
            }
        });
    }

    @Override
    public void getRouteInfo(final IDataManager.OnRouteIDListener routeIDListener) {

        Call<Route> routeCall = apiService.getRoute(
                41.914196,-88.308685,
                40.73061,-73.935242);
        routeCall.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                Log.d(TAG, "onResponse: " + response.body());
                routeItem = response.body().getRoute().get(0);
                routeIDListener.getRouteID(routeItem);
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                routeIDListener.getRouteID(routeItem);
            }
        });
    }

    @Override
    public void getBusInfo(final IDataManager.OnBusInfoListener busInfoListener) {

        Call<BusInformation> busCall = apiService.getBusInfo(1);
        busCall.enqueue(new Callback<BusInformation>() {
            @Override
            public void onResponse(Call<BusInformation> call, Response<BusInformation> response) {
                Log.d(TAG, "onResponse: " + response.body());
                businformationItem = response.body().getBusinformation().get(0);
                busInfoListener.getBusDetails(businformationItem);
            }

            @Override
            public void onFailure(Call<BusInformation> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                busInfoListener.getBusDetails(businformationItem);
            }
        });
    }

    @Override
    public void getSeatInfo(final IDataManager.OnSeatInfoListener seatInfoListener) {

        Call<SeatInformation> seatCall = apiService.getSeatInfo(102);
        seatCall.enqueue(new Callback<SeatInformation>() {
            @Override
            public void onResponse(Call<SeatInformation> call, Response<SeatInformation> response) {
                Log.d(TAG, "onResponse: " + response.body());
                seatinformationItem = response.body().getSeatinformation().get(0);
                seatInfoListener.getSeatDetails(seatinformationItem);
            }

            @Override
            public void onFailure(Call<SeatInformation> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                seatInfoListener.getSeatDetails(seatinformationItem);
            }
        });
    }

    @Override
    public void getCouponInfoList(final IDataManager.OnCouponListener couponListener) {

        Call<Coupon> couponCall = apiService.getCoupons();
        couponCall.enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                Log.d(TAG, "onResponse: " + response.body());
                couponList = new ArrayList<>(response.body().getCoupons());
                couponListener.getCouponList(couponList);
            }

            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                couponListener.getCouponList(couponList);
            }
        });
    }
}
