package com.example.potatopaloozac.traveldomainproj.ui.gameschedule;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.INetworkHelper;

public class PresenterGameSchedule implements IPresenterGameschedule, IDataManager.OnGameScheduleListener{

    IViewGameSchedule view;
    IDataManager dataManager;


    public PresenterGameSchedule(GameScheduleActivity gameScheduleActivity) {
        this.view = gameScheduleActivity;
        dataManager = new DataManager(gameScheduleActivity);
    }

    @Override
    public void findGame(String busdeparturetime, String journyduration) {
        dataManager.findGame(busdeparturetime, journyduration,this);
    }

    @Override
    public void updateGame(String game_info) {
        view.updateGame(game_info);
    }
}
