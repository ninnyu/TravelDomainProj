package com.example.potatopaloozac.traveldomainproj;

import com.example.potatopaloozac.traveldomainproj.adapter.BusSeat;
import com.example.potatopaloozac.traveldomainproj.adapter.GameSchedule;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testSeat() {
        BusSeat busSeat = new BusSeat(1, 10);
        int type = busSeat.getType();
        int seatNum = busSeat.getSeatNum();

        assertEquals(type,1);
        assertEquals(seatNum,10);
    }

    @Test
    public void testSchedule(){
        GameSchedule gameSchedule = new GameSchedule("9 pm TEAM1 TEAM2");
        String time = gameSchedule.getTime();
        String home = gameSchedule.getHome();
        String away = gameSchedule.getAway();

        assertEquals(time, "9 pm");
        assertEquals(home, "TEAM1");
        assertEquals(away,"TEAM2");
    }
}