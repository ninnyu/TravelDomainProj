package com.example.potatopaloozac.traveldomainproj.ui.transfer;

import android.util.Log;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.database.IDBHelper;
import com.example.potatopaloozac.traveldomainproj.data.network.INetworkHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


}
