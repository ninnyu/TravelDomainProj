package com.example.potatopaloozac.traveldomainproj.ui.orderhistory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.potatopaloozac.traveldomainproj.R

class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)


        /*
        create presenter, ipresenter, and iview
        this activity will show a list of the trips that the user has placed
        database should have columns:
                                        bus id
                                        start city name
                                        end city name
                                        date of departure
                                        departure time
                                        arrival time
                                        journey duration
                                        number of passengers
         */
    }
}
