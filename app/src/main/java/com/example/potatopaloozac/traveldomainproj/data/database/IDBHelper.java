package com.example.potatopaloozac.traveldomainproj.data.database;

import com.example.potatopaloozac.traveldomainproj.data.IDataManager;

import java.util.List;

public interface IDBHelper {

    void loadDataBase();
    List<String> findGame(String busdeparturetime, String journyduration, IDataManager.OnGameScheduleListener listener);
}
