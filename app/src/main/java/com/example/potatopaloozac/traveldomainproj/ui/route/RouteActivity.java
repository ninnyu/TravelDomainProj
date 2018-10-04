package com.example.potatopaloozac.traveldomainproj.ui.route;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.data.network.model.RouteItem;

public class RouteActivity extends AppCompatActivity implements IRouteView {

    TextView tv_route;

    IRoutePresenter routePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        tv_route = findViewById(R.id.tv_route);

        routePresenter = new RoutePresenter(this);

    }

    @Override
    public void showRouteDetails(RouteItem routeItem) {

        StringBuffer s = new StringBuffer();

        s.append("Route ID: ");
        s.append(routeItem.getId());
        s.append("\nRoute Name: ");
        s.append(routeItem.getRoutename());
        s.append("\nStart From: ");
        s.append(routeItem.getRouteStartfrom());
        s.append("\nDestination: ");
        s.append(routeItem.getRouteDestination());
        s.append("\nStart Latitude: ");
        s.append(routeItem.getRouteStartpointLatitude());
        s.append("\nStart Longitude: ");
        s.append(routeItem.getRouteStartpointLongitude());
        s.append("\nEnd Latitude: ");
        s.append(routeItem.getRouteEndpointLatitude());
        s.append("\nEnd Longitude: ");
        s.append(routeItem.getRouteEndpointLongiude());

        tv_route.setText(s);
    }
}
