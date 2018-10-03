package com.example.potatopaloozac.traveldomainproj.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private static final String BUS_INFO = "BUS_INFO";

    public static final String START_CITY_LAT = "START_CITY_LAT";
    public static final String START_CITY_LONG = "START_CITY_LONG";
    public static final String END_CITY_LAT = "END_CITY_LAT";
    public static final String END_CITY_LONG = "END_CITY_LONG";
    public static final String ROUTE_ID = "ROUTE_ID";
    public static final String BUS_ID = "BUS_ID";

    private static SharedPreferences sp;

    private MySharedPreference() {

    }

    public static void getSharedPreferences(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(BUS_INFO, Context.MODE_PRIVATE);
    }

    public static String readString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public static void writeString(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value).apply();
    }

    public static boolean readBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public static void writeBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value).apply();
    }

    public static int readInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public static void writeInt(String key, int value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value).apply();
    }

    public static double readDouble(String key, String defValue) {
        return Double.parseDouble(sp.getString(key, defValue));
    }

    public static void writeDouble(String key, double value) {
        String s = Double.toString(value);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, s).apply();
    }
}
