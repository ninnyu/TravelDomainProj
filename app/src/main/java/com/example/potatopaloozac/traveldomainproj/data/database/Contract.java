package com.example.potatopaloozac.traveldomainproj.data.database;

import android.provider.BaseColumns;

public class Contract {

    public static abstract class Entry implements BaseColumns{

        public static final String TABLE_NAME = "gameschedule";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_HOME = "hometeam";
        public static final String COLUMN_AWAY = "awayteam";

    }

}
