package com.example.potatopaloozac.traveldomainproj.ui.booking.businfo;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager.*;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.data.network.model.RouteItem;

public class BusInfoPresenter implements OnRouteIDListener, OnBusInfoListener, IBusInfoPresenter {

    private static final String TAG = "BusInfoPresenterTAG";

    IBusInfoView busInfoView;
    IDataManager dataManager;

    public BusInfoPresenter(BusInfoActivity activity) {
        busInfoView = activity;
        dataManager = new DataManager(activity);
    }

    @Override
    public void onActivityCreated() {
        dataManager.getRouteInfo(this);
    }

    @Override
    public void getRouteID(RouteItem routeItem) {
        dataManager.getBusInfo(this);
    }

    @Override
    public void getBusDetails(BusinformationItem businformationItem) {
        busInfoView.showBusDetails(businformationItem);
    }
}
