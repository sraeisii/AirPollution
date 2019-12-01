package com.example.airpollution.Model;

public class AirPollution {
    private int cityId;
    private String cityName;
    private String cityFaName;
    private float tp;
    private float hu;
    private float pr;
    private float wd;
    private float pollution;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityFaName() {
        return cityFaName;
    }

    public void setCityFaName(String cityFaName) {
        this.cityFaName = cityFaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public float getTp() {
        return tp;
    }

    public void setTp(float tp) {
        this.tp = tp;
    }

    public float getHu() {
        return hu;
    }

    public void setHu(float hu) {
        this.hu = hu;
    }

    public float getPr() {
        return pr;
    }

    public void setPr(float pr) {
        this.pr = pr;
    }

    public float getWd() {
        return wd;
    }

    public void setWd(float wd) {
        this.wd = wd;
    }

    public float getPollution() {
        return pollution;
    }

    public void setPollution(float pollution) {
        this.pollution = pollution;
    }
}
