package com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo2;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.SeatinformationItem;

public class SeatInfoPresenter2 implements ISeatInfoPresenter2, IDataManager.OnSeatInfoListener {

    ISeatInfoView2 seatInfoView;
    IDataManager dataManager;

    public SeatInfoPresenter2(SeatInfo2Activity activity) {
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
