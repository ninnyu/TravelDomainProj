package com.example.potatopaloozac.traveldomainproj.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
