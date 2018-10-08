package com.example.potatopaloozac.traveldomainproj.ui.booking.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ActionMenuView;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.ui.booking.businfo.BusInfoActivity;
import com.example.potatopaloozac.traveldomainproj.ui.booking.passengerdetails.PassengerDetailsActivity;
import com.example.potatopaloozac.traveldomainproj.ui.booking.seatinfo.SeatInfoActivity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;

import java.sql.Timestamp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends AppCompatActivity {

    private static final String TAG = "PaymentActivityTAG";

    @BindView(R.id.tv_payment_leavingFrom)
    TextView tvPaymentLeavingFrom;
    @BindView(R.id.tv_payment_goingTo)
    TextView tvPaymentGoingTo;
    @BindView(R.id.tv_payment_passengerNum)
    TextView tvPaymentPassengerNum;
    @BindView(R.id.tv_payment_perPerson)
    TextView tvPaymentPerPerson;
    @BindView(R.id.tv_payment_total)
    TextView tvPaymentTotal;

    private int seatCount;
    private BusinformationItem businformationItem;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        seatCount = getIntent().getIntExtra("seatcount", 0);
        businformationItem = getIntent().getParcelableExtra("businfo");

        total = Integer.parseInt(businformationItem.getFare()) * seatCount;

        String start = MySharedPreference.readString(MySharedPreference.START_CITY_NAME, "") + "\n" +
                businformationItem.getBusdeparturetime();
        String end = MySharedPreference.readString(MySharedPreference.END_CITY_NAME, "") + "\n" +
                businformationItem.getDropingtime();

        tvPaymentLeavingFrom.setText(start);
        tvPaymentGoingTo.setText(end);
        tvPaymentPassengerNum.setText(Integer.toString(seatCount));
        tvPaymentPerPerson.setText(businformationItem.getFare());
        tvPaymentTotal.setText(Integer.toString(total));
    }

    @OnClick(R.id.bt_paymentConfirm)
    public void onViewClicked() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = timestamp.toString();

        Intent i = new Intent(this, PaymentConfirmationActivity.class);
        i.putExtra("businfo", businformationItem);
        i.putExtra("seatcount", seatCount);
        i.putExtra("total", total);
        i.putExtra("time", time);
        startActivity(i);

        BusInfoActivity.activity.finish();
        SeatInfoActivity.activity.finish();
        PassengerDetailsActivity.activity.finish();
        finish();
    }
}
