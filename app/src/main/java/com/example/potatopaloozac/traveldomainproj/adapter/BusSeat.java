package com.example.potatopaloozac.traveldomainproj.adapter;

public class BusSeat {

    int type, seatNum;

    //Type 0: available
    //     1: reserved
    //     2: selected
    public BusSeat(int type, int seatNum) {
        this.type = type;
        this.seatNum = seatNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSeatNum() {
        return seatNum;
    }
}
