package com.example.potatopaloozac.traveldomainproj.data.database;

import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;

import java.util.List;

public interface IDBHelper {

    void loadDataBase();
    void findGame(String busdeparturetime, String journyduration, IDataManager.OnGameScheduleListener listener);
    void saveCity(List<CityItem> cityItemList);
    void findTransfer(String city_start, String city_destination, IDataManager.OnTransferListener listener);
}
