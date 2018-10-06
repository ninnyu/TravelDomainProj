package com.example.potatopaloozac.traveldomainproj.ui.gameschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.adapter.GameSchedule;
import com.example.potatopaloozac.traveldomainproj.adapter.GameScheduleAdapter;
import com.example.potatopaloozac.traveldomainproj.ui.booking.BookingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameScheduleActivity extends AppCompatActivity implements IViewGameSchedule {

    private static final String TAG = "GameScheduleActivityTAG";

    @BindView(R.id.bt_home)
    Button btHome;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.bt_schedule)
    Button btSchedule;
    @BindView(R.id.bt_trips)
    Button btTrips;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private IPresenterGameschedule presenter;
    private List<GameSchedule> mygamelist;
    private GameScheduleAdapter adapter;
    private RecyclerView recyclerView_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_schedule);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        presenter = new PresenterGameSchedule(this);
        mygamelist = new ArrayList<>();
        recyclerView_game = findViewById(R.id.recyclerView_game);
        adapter = new GameScheduleAdapter(mygamelist, new GameScheduleAdapter.GameScheduleOnClickListener() {
            @Override
            public void onItemClick(GameSchedule gameSchedule) {

            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(GameScheduleActivity.this);
        recyclerView_game.setLayoutManager(manager);
        recyclerView_game.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        String bus_departure = b.getString("bus_departure");
        String[] s = (b.getString("bus_duration")).split("Hrs");
        String bus_duration = s[0] + ":00:00 Hrs";

        /**{
         "Businformation":
         [
         {"busid
         ":"102","busregistrationno":"TC102",
         "bustype":"Sleeper,AC,LCD","
         busdeparturetime":"09:00:00 PM",
         "journyduration":"08:00:00 Hrs",
         "Fare":"700",
         "boardingtime":"07:15:00 AM",
         "dropingtime":"04:00:00 AM"}
         ]}
         */

        presenter.findGame(bus_departure, bus_duration);
    }


    @Override
    public void updateGame(String game_info) {
        GameSchedule gameSchedule = new GameSchedule(game_info);
        mygamelist.add(gameSchedule);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.bt_home, R.id.bt_search, R.id.bt_schedule, R.id.bt_trips})
    public void onViewClicked(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.bt_home:
                break;
            case R.id.bt_search:
                i = new Intent(this, BookingActivity.class);
                startActivity(i);
                break;
            case R.id.bt_schedule:
                break;
            case R.id.bt_trips:
                break;
        }
    }
}
