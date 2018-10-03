package com.example.potatopaloozac.traveldomainproj.ui.city;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.CityItem;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity implements ICityView {

    TextView tv_cityList;

    ICityPresenter cityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        tv_cityList = findViewById(R.id.tv_cityList);

        cityPresenter = new CityPresenter(this);
        cityPresenter.onActivityCreated();
    }

    @Override
    public void showCityList(ArrayList<CityItem> cityList) {

        StringBuffer s = new StringBuffer();

        for (int i = 0; i < cityList.size(); i++) {
            s.append("City Name: ");
            s.append(cityList.get(i).getCityname());
            s.append("\n City Longitude: ");
            s.append(cityList.get(i).getCitylongtitude());
            s.append("\n City Latitude: ");
            s.append(cityList.get(i).getCitylatitude());
            s.append("\n\n");
        }

        tv_cityList.setText(s);
    }
}
