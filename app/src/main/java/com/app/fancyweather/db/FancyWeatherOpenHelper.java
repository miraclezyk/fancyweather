package com.app.fancyweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fire on 2016/7/8.
 */
public class FancyWeatherOpenHelper extends SQLiteOpenHelper{

    public static final String CREATE_STATE = "create table State ("
            + "id integer primary key autoincrement,"
            + "state_name text,"
            + "state_code text)";

    public static final String CREATE_COUNTY = "create table County ("
            + "id integer primary key autoincrement,"
            + "county_name text,"
            + "county_code text,"
            + "state_id integer)";

    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement,"
            + "city_name text,"
            + "city_code text,"
            + "county_id integer)";

    public FancyWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_STATE);
        sqLiteDatabase.execSQL(CREATE_COUNTY);
        sqLiteDatabase.execSQL(CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
