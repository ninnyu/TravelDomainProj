package com.example.potatopaloozac.traveldomainproj.ui.seat;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatinformationItem;

public class SeatInfoPresenter implements ISeatInfoPresenter, IDataManager.OnSeatInfoListener {

    ISeatInfoView seatInfoView;
    IDataManager dataManager;

    public SeatInfoPresenter(SeatInfoActivity activity) {
        seatInfoView = activity;
        dataManager = new DataManager(activity);
    }

    @Override
    public void onActivityCreated() {
        dataManager.getSeatInfo(this);
    }

    @Override
    public void getSeatDetails(SeatinformationItem seatinformationItem) {
        seatInfoView.showSeatInfo(seatinformationItem);
    }
}
