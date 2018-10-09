package com.example.potatopaloozac.traveldomainproj.ui.coupon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CouponsItem;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity implements ICouponView {

    TextView tv_couponList;

    ICouponPresenter couponPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        tv_couponList = findViewById(R.id.tv_couponList);

        couponPresenter = new CouponPresenter(this);
        couponPresenter.onActivityCreated();
    }

    @Override
    public void showCouponList(ArrayList<CouponsItem> couponList) {

        StringBuffer s = new StringBuffer();

        for (int i = 0; i < couponList.size(); i++) {
            s.append("ID: ");
            s.append(couponList.get(i).getId());
            s.append("\nCoupon Number: ");
            s.append(couponList.get(i).getCouponno());
            s.append("\nDiscount: ");
            s.append(couponList.get(i).getDiscount());
            s.append("\n\n");
        }

        tv_couponList.setText(s);
    }
}
