package com.example.potatopaloozac.traveldomainproj.data.network;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.potatopaloozac.traveldomainproj.R;
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
import com.example.potatopaloozac.traveldomainproj.ui.booking.transfer.TransferActivity;
import com.example.potatopaloozac.traveldomainproj.utils.ApiService;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;
import com.example.potatopaloozac.traveldomainproj.utils.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkHelper implements INetworkHelper {

    private static final String TAG = "NetworkHelperTAG";

    private ArrayList<CityItem> cityList;
    private RouteItem routeItem;
    private BusinformationItem businformationItem;
    private SeatinformationItem seatinformationItem;
    private ArrayList<CouponsItem> couponList;

    private Context context;
    private ApiService apiService;
    private ProgressDialog progressDialog;

    private boolean flag_start_transfer;
    private boolean flag_transfer_destination;
    //String city_transfer;

    public NetworkHelper(Context context) {
        this.context = context;
        this.apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public void getCityInfoList(final IDataManager.OnCityListener cityListener) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.loadingDataTitle);
        progressDialog.setMessage(context.getResources().getString(R.string.loadingDataMessage));
        progressDialog.show();

        Call<City> cityCall = apiService.getCities();
        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    cityList = new ArrayList<>(response.body().getCity());
                    cityListener.getCityList(cityList);
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle(R.string.cannotLoadDataTitle);
                    alertDialogBuilder.setMessage(R.string.cannotLoadDataMessage);

                    alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialogBuilder.show();
                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle(R.string.cannotLoadDataTitle);
                alertDialogBuilder.setMessage(R.string.cannotLoadDataMessage);

                alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialogBuilder.show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getRouteInfo(final IDataManager.OnRouteIDListener routeIDListener) {

        double start_lat = Double.parseDouble(MySharedPreference.readString(MySharedPreference.START_CITY_LAT, "")),
                start_long = Double.parseDouble(MySharedPreference.readString(MySharedPreference.START_CITY_LONG, "")),
                end_lat = Double.parseDouble(MySharedPreference.readString(MySharedPreference.END_CITY_LAT, "")),
                end_long = Double.parseDouble(MySharedPreference.readString(MySharedPreference.END_CITY_LONG, ""));

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.loadingDataTitle);
        progressDialog.setMessage(context.getResources().getString(R.string.loadingDataMessage));
        progressDialog.show();

        Call<Route> routeCall = apiService.getRoute(start_lat, start_long, end_lat, end_long);
        routeCall.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if (response.body().getRoute() != null) {
                    routeItem = response.body().getRoute().get(0);
                    MySharedPreference.writeInt(MySharedPreference.ROUTE_ID, Integer.parseInt(routeItem.getId()));
                } else {
                    progressDialog.dismiss();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle(R.string.transferTitle);
                    alertDialogBuilder.setMessage(R.string.transferMessage);

                    alertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(context, TransferActivity.class);
                            context.startActivity(i);
                        }
                    });
                    alertDialogBuilder.show();
                }
                routeIDListener.getRouteID(routeItem);
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getBusInfo(final IDataManager.OnBusInfoListener busInfoListener) {

        int routeID = MySharedPreference.readInt(MySharedPreference.ROUTE_ID, 0);

        Call<BusInformation> busCall = apiService.getBusInfo(routeID);
        busCall.enqueue(new Callback<BusInformation>() {
            @Override
            public void onResponse(Call<BusInformation> call, Response<BusInformation> response) {
                progressDialog.dismiss();
                if(response.body().getBusinformation()!=null) {
                    businformationItem = response.body().getBusinformation().get(0);
                    MySharedPreference.writeInt(MySharedPreference.BUS_ID, Integer.parseInt(businformationItem.getBusid()));
                    busInfoListener.getBusDetails(businformationItem);
                }
            }

            @Override
            public void onFailure(Call<BusInformation> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getSeatInfo(final IDataManager.OnSeatInfoListener seatInfoListener) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.loadingDataTitle);
        progressDialog.setMessage(context.getResources().getString(R.string.loadingDataMessage));
        progressDialog.show();

        int busID = MySharedPreference.readInt(MySharedPreference.BUS_ID, 0);

        Call<SeatInformation> seatCall = apiService.getSeatInfo(busID);
        seatCall.enqueue(new Callback<SeatInformation>() {
            @Override
            public void onResponse(Call<SeatInformation> call, Response<SeatInformation> response) {
                progressDialog.dismiss();
                seatinformationItem = response.body().getSeatinformation().get(0);
                seatInfoListener.getSeatDetails(seatinformationItem);
            }

            @Override
            public void onFailure(Call<SeatInformation> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getCouponInfoList(final IDataManager.OnCouponListener couponListener) {

        Call<Coupon> couponCall = apiService.getCoupons();
        couponCall.enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                couponList = new ArrayList<>(response.body().getCoupons());
                couponListener.getCouponList(couponList);
            }

            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void findRoute(String city_start, String city_destination, String city_transfer, final IDataManager.OnTransferListener listener) {

        String[] city_transfer_split = city_transfer.split("_");
        final String city_nm =  city_transfer_split[0];
        String city_lat = city_transfer_split[1];
        String city_long = city_transfer_split[2];

        //Log.d("MyTransfer", city_nm+" "+city_lat+" "+city_long);

        final double transfer_lat = Double.parseDouble(city_lat);
        final double transfer_long = Double.parseDouble(city_long);

        flag_start_transfer = false;
        flag_transfer_destination = false;

        final double start_lat = Double.parseDouble(MySharedPreference.readString(MySharedPreference.START_CITY_LAT, "")),
                start_long = Double.parseDouble(MySharedPreference.readString(MySharedPreference.START_CITY_LONG, "")),
                end_lat = Double.parseDouble(MySharedPreference.readString(MySharedPreference.END_CITY_LAT, "")),
                end_long = Double.parseDouble(MySharedPreference.readString(MySharedPreference.END_CITY_LONG, ""));

        Call<Route> routeCall = apiService.getRoute(start_lat, start_long, transfer_lat,transfer_long);
        routeCall.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                Log.d("MyTransfer", ""+ start_lat+ " "+ start_long+" "+city_nm);
                Log.d("MyTransfer", "onResponse: " + response.body());
                //routeItem = response.body().getRoute().get(0);
                if (response.body().getRoute() != null){
                    Log.d("MyTransfer", "good");
                    flag_start_transfer = true;
                    listener.addStartTransfer(city_nm,true);
                }
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                //Log.d("MyTransfer", ""+ start_lat+ " "+ start_long+" "+city_nm);
                //Log.d("MyTransfer", "onFailure: " + t.getMessage());
                listener.addStartTransfer(city_nm,false);
            }
        });

        routeCall = apiService.getRoute(transfer_lat,transfer_long, end_lat, end_long);
        routeCall.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                Log.d("MyTransfer", ""+ start_lat+ " "+ start_long+" "+city_nm);
                Log.d("MyTransfer", "onResponse: " + response.body());
                //routeItem = response.body().getRoute().get(0);
                if (response.body().getRoute() != null){
                    flag_transfer_destination = true;
                    Log.d("MyTransfer", "good");
                    listener.addTransferDestination(city_nm,true);
                }
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                //Log.d("MyTransfer", ""+ start_lat+ " "+ start_long+" "+city_nm);
                //Log.d("MyTransfer", "onFailure: " + t.getMessage());
                listener.addTransferDestination(city_nm,false);
            }
        });

    }


}
