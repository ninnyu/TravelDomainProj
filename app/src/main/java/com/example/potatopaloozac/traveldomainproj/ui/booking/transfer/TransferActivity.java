package com.example.potatopaloozac.traveldomainproj.ui.booking.transfer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferActivity extends AppCompatActivity implements IViewTransfer, OnMapReadyCallback {

    String city_start, city_destination;
    IPresenterTransfer presenter;
    TextView textView_start_transfer, textView_transfer_destination;

    Map<String, Boolean> map_start_transfer, map_transfer_destination;
    double city_lat, city_long;

    private GoogleMap mMap;
    Polyline polyline;
    LatLng start, destination, transfer;
    int idx = 0;
    List<String> msg_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        textView_start_transfer = findViewById(R.id.textView_transfer1);
        //textView_transfer_destination = findViewById(R.id.textView_transfer2);

        //textView_start_transfer.setText("Sorry, no transfer available");
        msg_list = new ArrayList<>();
        map_start_transfer = new HashMap<>();
        map_transfer_destination = new HashMap<>();

        city_start = MySharedPreference.readString(MySharedPreference.START_CITY_NAME, "");
        city_destination = MySharedPreference.readString(MySharedPreference.END_CITY_NAME, "");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        presenter = new PresenterTransfer(this);

        //Log.d("MyTransfer", city_start+ " "+city_destination);
        presenter.findTransfer(city_start, city_destination);


    }


    @Override
    public void showTransfer(String city_nm) {
        //textView_start_transfer.setText("From: "+ city_start+" to: "+ city_nm);
        //textView_transfer_destination.setText("From: "+ city_nm+" to: "+ city_destination);

        //String msg =  "From: "+ city_start+" to: "+ city_nm + "\n"
        //            + "From: "+ city_nm+" to: "+ city_destination + "\n";

        msg_list.add( "From: "+ city_start+" to: "+ city_nm );
        msg_list.add("From: "+ city_nm+" to: "+ city_destination);

        //textView_start_transfer.setText(msg_array[idx]);

        presenter.getCityInfo(city_nm);
        transfer = new LatLng(city_lat, city_long);

        presenter.getRouteInfo(start,destination, transfer);

        mMap.addMarker(new MarkerOptions().position(transfer).title("Transfer"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(transfer,4));

        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        start,
                        transfer,
                        destination

                        ));

    }

    @Override
    public void setCityInfo(String city_info) {
        String[] city_info_split = city_info.split(" ");
        city_lat = Double.parseDouble(city_info_split[0]);
        city_long = Double.parseDouble(city_info_split[1]);
    }

    @Override
    public void showStartTransRoute(String info) {
        Log.d("MyRouteInfo", info);
    }

    @Override
    public void showTransDesRoute(String info) {
        Log.d("MyRouteInfo", info);
    }

    @Override
    public void setBusInfo(String busInfo) {
        String msg = textView_start_transfer.getText().toString();
        textView_start_transfer.setText(msg+"\n"+ msg_list.get(idx)+"\n"+ "Bus Information: "+"\n"+busInfo+"\n");
        idx++;
        Log.d("MyInfo", msg+"\n"+busInfo);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.d("MyMap", city_start);
        presenter.getCityInfo(city_start);
        start = new LatLng(city_lat, city_long);
        mMap.addMarker(new MarkerOptions().position(start).title("Start"));


        presenter.getCityInfo(city_destination);
        destination = new LatLng(city_lat, city_long);
        mMap.addMarker(new MarkerOptions().position(destination).title("Destination"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination,4));



    }
}
