package com.app.fancyweather.model;

/**
 * Created by Fire on 2016/7/8.
 */
public class State {
    private int id;
    private String stateName;
    private String stateCode;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
