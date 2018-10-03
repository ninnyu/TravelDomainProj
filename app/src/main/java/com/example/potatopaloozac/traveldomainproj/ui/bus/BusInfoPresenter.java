package com.example.potatopaloozac.traveldomainproj.ui.bus;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;

public class BusInfoPresenter implements IBusInfoPresenter, IDataManager.OnBusInfoListener {

    IBusInfoView busInfoView;
    IDataManager dataManager;

    public BusInfoPresenter(BusInfoActivity activity) {
        busInfoView = activity;
        dataManager = new DataManager(activity);
    }

    @Override
    public void onActivityCreated() {
        dataManager.getBusInfo(this);
    }

    @Override
    public void getBusDetails(BusinformationItem businformationItem) {
        busInfoView.showBusDetails(businformationItem);
    }
}
