package com.example.potatopaloozac.traveldomainproj.ui.coupon;

import com.example.potatopaloozac.traveldomainproj.data.DataManager;
import com.example.potatopaloozac.traveldomainproj.data.IDataManager;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CouponsItem;

import java.util.ArrayList;

public class CouponPresenter implements ICouponPresenter, IDataManager.OnCouponListener {

    ICouponView couponView;
    IDataManager dataManager;

    public CouponPresenter(CouponActivity activity) {
        couponView = activity;
        dataManager = new DataManager(activity);
    }

    @Override
    public void onActivityCreated() {
        dataManager.getCouponInfoList(this);
    }

    @Override
    public void getCouponList(ArrayList<CouponsItem> couponList) {
        couponView.showCouponList(couponList);
    }
}
