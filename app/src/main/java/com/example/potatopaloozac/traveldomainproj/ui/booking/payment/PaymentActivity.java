package com.example.potatopaloozac.traveldomainproj.ui.booking.payment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.potatopaloozac.traveldomainproj.R;

public class PaymentActivity extends AppCompatActivity implements IPaymentView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }
}
