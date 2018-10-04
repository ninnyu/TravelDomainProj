package com.example.potatopaloozac.traveldomainproj.ui.route;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.RouteItem;

public class RoutePresenter implements IRoutePresenter, IDataManager.OnRouteIDListener {

    IRouteView routeView;
    IDataManager dataManager;

    public RoutePresenter(RouteActivity activity) {
        routeView = activity;
        dataManager = new DataManager(activity);
    }



    @Override
    public void getRouteID(RouteItem routeItem) {
        routeView.showRouteDetails(routeItem);
    }
}
