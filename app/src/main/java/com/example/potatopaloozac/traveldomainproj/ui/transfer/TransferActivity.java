package com.example.potatopaloozac.traveldomainproj.ui.transfer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferActivity extends AppCompatActivity implements IViewTransfer {

    String city_start, city_destination;
    IPresenterTransfer presenter;
    TextView textView_start_transfer, textView_transfer_destination;

    Map<String, Boolean> map_start_transfer, map_transfer_destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        textView_start_transfer = findViewById(R.id.textView_transfer1);
        textView_transfer_destination = findViewById(R.id.textView_transfer2);

        textView_start_transfer.setText("Sorry, no transfer available");

        map_start_transfer = new HashMap<>();
        map_transfer_destination = new HashMap<>();

        city_start = MySharedPreference.readString(MySharedPreference.START_CITY_NAME, "");
        city_destination = MySharedPreference.readString(MySharedPreference.END_CITY_NAME, "");

        presenter = new PresenterTransfer(this);

        //Log.d("MyTransfer", city_start+ " "+city_destination);
        presenter.findTransfer(city_start, city_destination);


    }


    @Override
    public void showTransfer(String city_nm) {
        textView_start_transfer.setText("From: "+ city_start+" to: "+ city_nm);
        textView_transfer_destination.setText("From: "+ city_nm+" to: "+ city_destination);
    }
}
