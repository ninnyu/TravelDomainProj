package com.example.potatopaloozac.traveldomainproj.ui.coupon;

import com.example.potatopaloozac.traveldomainproj.data.network.model.CouponsItem;

import java.util.ArrayList;

public interface ICouponView {
    void showCouponList(ArrayList<CouponsItem> couponList);
}
