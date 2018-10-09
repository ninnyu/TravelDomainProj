package com.example.potatopaloozac.traveldomainproj.ui.booking.transfer;

import android.util.Log;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

public class PresenterTransfer implements IPresenterTransfer, IDataManager.OnTransferListener {

    IViewTransfer view;
    IDataManager manager;
    Map<String, Boolean> map_start_transfer, map_transfer_destination;

    public PresenterTransfer(TransferActivity transferActivity) {
        this.view = transferActivity;
        manager = new DataManager(transferActivity);
        map_start_transfer = new HashMap<>();
        map_transfer_destination = new HashMap<>();
    }


    @Override
    public void findTransfer(String city_start, String city_destination) {
        manager.findTransfer(city_start, city_destination, this);
    }

    @Override
    public void getCityInfo(String city_nm) {
        manager.getCityInfo(city_nm, this);
    }

    @Override
    public void getRouteInfo(LatLng city_start, LatLng city_destination, LatLng city_transfer) {
        manager.getRouteInfo(city_start, city_destination, city_transfer, this);
    }

    @Override
    public void getBusInfo(String routeid) {

    }

    @Override
    public void findRoute(String city_start, String city_destination, String city_transfer) {
        manager.findRoute(city_start, city_destination, city_transfer, this);
    }

    @Override
    public void addStartTransfer(String city_nm, boolean flag) {
        //list_start_transfer.add(flag);
        //view.addStartTransfer(city_nm,flag);
        //Log.d("MyFlag1", city_nm+" "+flag);
        if(flag){
            map_start_transfer.put(city_nm, flag);
            //Log.d("MyFlag1", ""+map_transfer_destination.get(city_nm));
        }
        if(map_transfer_destination.get(city_nm)!=null){
            if(map_transfer_destination.get(city_nm)) {
                //Log.d("MyTF", city_nm);
                view.showTransfer(city_nm);
            }
        }
    }

    @Override
    public void addTransferDestination(String city_nm, boolean flag) {
        //list_transfer_destination.add(flag);
        //view.addTransferDestination(city_nm, flag);
        //Log.d("MyFlag2", city_nm+" "+flag);
        if(flag){
            map_transfer_destination.put(city_nm, flag);
            //Log.d("MyFlag2", ""+map_start_transfer.get(city_nm));
        }

        if(map_start_transfer.get(city_nm)!=null){
            //Log.d("MyFlag2", city_nm);
            if(map_start_transfer.get(city_nm)) {
                //Log.d("MyTF", city_nm);
                view.showTransfer(city_nm);
            }
        }
    }

    @Override
    public void setCityInfo(String city_info) {
        view.setCityInfo(city_info);
    }

    @Override
    public void showStartTransRoute(String route_info) {
        //view.showStartTransRoute(route_info);
        manager.getBusInfoStartTrans(route_info,this);
    }

    @Override
    public void showTransDesRoute(String route_info) {
        //view.showTransDesRoute(route_info);
        //manager.getBusInfo(route_info,this);
        manager.getBusInfoTransDes(route_info, this);
    }

    @Override
    public void showBusStartTrans(BusinformationItem bus_info) {
        Log.d("MyPresenter", bus_info.toString());
        String msg= "Registration Number: "+bus_info.getBusregistrationno()+"\n"
                   +"Bus Type: "+ bus_info.getBustype()+"\n"
                   +"Departure Time: "+ bus_info.getBusdeparturetime() +"\n"
                   +"Journey Duration: "+ bus_info.getJournyduration() +"\n"
                   + "Droping Time: "+ bus_info.getDropingtime();
        view.setBusInfoStartTrans(msg);
    }

    @Override
    public void showBusTransDes(BusinformationItem bus_info) {
        String msg= "Registration Number: "+bus_info.getBusregistrationno()+"\n"
                +"Bus Type: "+ bus_info.getBustype()+"\n"
                +"Departure Time: "+ bus_info.getBusdeparturetime() +"\n"
                +"Journey Duration: "+ bus_info.getJournyduration() +"\n"
                + "Droping Time: "+ bus_info.getDropingtime();
        view.setBusInfoTransDes(msg);
    }


}
