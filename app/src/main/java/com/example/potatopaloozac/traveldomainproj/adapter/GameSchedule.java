package com.example.potatopaloozac.traveldomainproj.adapter;

public class GameSchedule {
    String time, home, away;

    public GameSchedule(String game_info) {
        String[] game_info_split = game_info.split(" ");
        time = game_info_split[0]+" "+ game_info_split[1];
        home = game_info_split[2];
        away = game_info_split[3];
    }

    public String getTime() {
        return time;
    }

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }
}
