package com.app.fancyweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.fancyweather.model.City;
import com.app.fancyweather.model.County;
import com.app.fancyweather.model.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fire on 2016/7/8.
 */
public class FancyWeatherDB {
    public static final String DB_NAME = "fancy_weather";
    public static final int VERSION = 1;
    private static FancyWeatherDB fancyWeatherDB;
    private SQLiteDatabase db;

    private FancyWeatherDB(Context context){
        FancyWeatherOpenHelper dbHelper = new FancyWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static FancyWeatherDB getInstance(Context context){
        if(fancyWeatherDB == null){
            fancyWeatherDB = new FancyWeatherDB(context);
        }
        return fancyWeatherDB;
    }

    public void saveState(State state){
        if(state != null){
            ContentValues values = new ContentValues();
            values.put("state_name", state.getStateName());
            values.put("state_code", state.getStateCode());
            db.insert("State", null, values);
        }
    }

    public List<State> loadStates(){
        List<State> list = new ArrayList<State>();
        Cursor cursor = db.query("State", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                State state = new State();
                state.setId(cursor.getInt(cursor.getColumnIndex("id")));
                state.setStateName(cursor.getString(cursor.getColumnIndex("state_name")));
                state.setStateCode(cursor.getString(cursor.getColumnIndex("state_code")));
                list.add(state);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public void saveCounty(County county){
        if(county != null){
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("state_id", county.getStateId());
            db.insert("County", null, values);
        }
    }

    public List<County> loadCounties(int stateId){
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "state_id = ?", new String[]{String.valueOf(stateId)}, null, null, null);
        if(cursor.moveToFirst()){
            do{
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setStateId(cursor.getInt(cursor.getColumnIndex("stateId")));
                list.add(county);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("county_id", city.getCountyId());
            db.insert("City", null, values);
        }
    }

    public List<City> loadCities(int countyId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "county_id = ?", new String[]{String.valueOf(countyId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCountyId(cursor.getInt(cursor.getColumnIndex("countyId")));
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
