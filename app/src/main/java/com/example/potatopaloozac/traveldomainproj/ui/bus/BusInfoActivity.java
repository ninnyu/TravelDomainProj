package com.example.potatopaloozac.traveldomainproj.ui.bus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.BusinformationItem;

public class BusInfoActivity extends AppCompatActivity implements IBusInfoView {

    TextView tv_busInfo;

    IBusInfoPresenter busInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_info);

        tv_busInfo = findViewById(R.id.tv_busInfo);

        busInfoPresenter = new BusInfoPresenter(this);
        busInfoPresenter.onActivityCreated();
    }

    @Override
    public void showBusDetails(BusinformationItem businformationItem) {

        StringBuffer s = new StringBuffer();

        s.append("Bus ID: ");
        s.append(businformationItem.getBusid());
        s.append("\nBus Registration Number: ");
        s.append(businformationItem.getBusregistrationno());
        s.append("\nBus Type: ");
        s.append(businformationItem.getBustype());
        s.append("\nBus Departure Time: ");
        s.append(businformationItem.getBusdeparturetime());
        s.append("\nJourney Duration: ");
        s.append(businformationItem.getJournyduration());
        s.append("\nFare: ");
        s.append(businformationItem.getFare());
        s.append("\nBoarding Time: ");
        s.append(businformationItem.getBoardingtime());
        s.append("\nDropping Time: ");
        s.append(businformationItem.getDropingtime());

        tv_busInfo.setText(s);
    }
}
