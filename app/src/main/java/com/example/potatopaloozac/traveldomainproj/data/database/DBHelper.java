package com.example.potatopaloozac.traveldomainproj.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.potatopaloozac.traveldomainproj.data.IDataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DBHelper implements IDBHelper {

    Context context;
    MyDataBase myDataBase;
    SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        this.context = context;
        myDataBase = new MyDataBase(context);
        sqLiteDatabase = myDataBase.getWritableDatabase();

    }

    @Override
    public void loadDataBase() {

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ Contract.Entry.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            Log.d("loadDB", "not empty");
//            String cmd = "DELETE FROM " + Contract.Entry.TABLE_NAME;
//            sqLiteDatabase.execSQL(cmd);
        }
        else{
            Log.d("loadDB", "empty");
            try {
                BufferedReader br=new BufferedReader(new InputStreamReader(context.getAssets().open("nba.csv")));
                String inputLine;
                int count = 0;
                while ((inputLine = br.readLine()) != null) {
                    Log.d("loadDB", inputLine);
                    count++;
                    if(count>30){
                        break;
                    }

                    String[] inputLine_split = inputLine.split(",");
                    int len = inputLine_split.length;
                    String s = "";
                    for(int i=0; i<len; i++){
                        s = s + inputLine_split[i] + " "+i+ " ";
                    }
                    Log.d("loadDBs", s);
                    //UTA POR 10:00 pm  "Tuesday  October 25"
                    String home = inputLine_split[1];
                    String away = inputLine_split[2];
                    String time = inputLine_split[3];
                    String date = inputLine_split[5];

                    ContentValues values = new ContentValues();
                    values.put(Contract.Entry.COLUMN_DATE, date);
                    values.put(Contract.Entry.COLUMN_TIME, time);
                    values.put(Contract.Entry.COLUMN_HOME, home);
                    values.put(Contract.Entry.COLUMN_AWAY, away);

                    sqLiteDatabase.insert(Contract.Entry.TABLE_NAME, null, values);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public List<String> findGame(String busdeparturetime, String journyduration, IDataManager.OnGameScheduleListener listener) {
//         busdeparturetime":"09:00:00 PM",
//         "journyduration":"08:00:00 Hrs",
        String[] busdeparturetime_split = busdeparturetime.split(" ");

        String[] journyduration_split = journyduration.split(":");

        String[] busdeparturetime_hms = busdeparturetime_split[0].split(":");
        int busdeparture_hr = Integer.parseInt(busdeparturetime_hms[0]);
        int busdeparture_mn = Integer.parseInt(busdeparturetime_hms[1]);

        int journyduration_hr = Integer.parseInt(journyduration_split[0]);
        int journyduration_mn = Integer.parseInt(journyduration_split[1]);

        //Log.d("MyGame", " "+ busdeparture_hr+" "+busdeparture_mn+" "+journyduration_hr+" "+journyduration_mn);

        if(busdeparturetime_split[1].equals("PM")){
            busdeparture_hr = busdeparture_hr + 12;
        }
        int busdrop_hr = busdeparture_hr + journyduration_hr;
        Log.d("MyGame", " "+ busdeparture_hr+" "+ busdrop_hr);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ Contract.Entry.TABLE_NAME, null);
        cursor.moveToFirst();

        //Log.d("MyGame", " "+flag);
        do{
            String time = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_TIME));
            String home = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_HOME));
            String away = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_AWAY));

            String[] time_split = time.split(" ");
            //10:00 pm
            String time_hm = time_split[0];
            String[] time_hm_split = time_hm.split(":");

            //for(String s: time_split) {
            //Log.d("MyGame", time);
            //Log.d("MyGame", ""+time_split[1].equals("pm"));
            //}
            int game_hr = Integer.parseInt(time_hm_split[0]);
            if(time_split[1].equals("pm")){
                //Log.d("MyGame", "true");
               game_hr = game_hr + 12;
            }
            Log.d("MyGame", " "+ game_hr);
            if( (game_hr < busdrop_hr) && (game_hr + 1 > busdeparture_hr ) ){
                String game_info = time + " "+home +" "+away;
                //Log.d("MyGame", game_info);
                listener.updateGame(game_info);
            }


        }while(cursor.moveToNext());

        return null;
    }


}
