package com.example.potatopaloozac.traveldomainproj.ui.booking.passengerdetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.adapter.PassengerDetailsAdapter;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;
import com.example.potatopaloozac.traveldomainproj.ui.booking.payment.PaymentActivity;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;

public class PassengerDetailsActivity extends AppCompatActivity {

    private static final String TAG = "PassengerTAG";

    private RecyclerView rv_passengerDetails;
    private Button bt_pay;
    private int seatCount;

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);

        activity = this;

        MySharedPreference.writeBoolean(MySharedPreference.PASSENGER_VALIDATED, false);

        seatCount = getIntent().getIntExtra("seatcount", 0);
        Log.d(TAG, "onCreate: " + seatCount);

        rv_passengerDetails = findViewById(R.id.rv_passengerDetails);
        bt_pay = findViewById(R.id.bt_pay);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv_passengerDetails.setLayoutManager(manager);
        rv_passengerDetails.setItemAnimator(new DefaultItemAnimator());

        PassengerDetailsAdapter adapter = new PassengerDetailsAdapter(this, seatCount);
        rv_passengerDetails.setAdapter(adapter);

        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MySharedPreference.readBoolean(MySharedPreference.PASSENGER_VALIDATED, false)) {
                    BusinformationItem businformationItem = getIntent().getParcelableExtra("businfo");
                    Intent i = new Intent(PassengerDetailsActivity.this, PaymentActivity.class);
                    i.putExtra("seatcount", seatCount);
                    i.putExtra("businfo", businformationItem);
                    startActivity(i);
                } else {
                    Toast.makeText(PassengerDetailsActivity.this, "Fields are not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
