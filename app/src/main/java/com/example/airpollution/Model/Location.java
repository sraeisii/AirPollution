package com.example.airpollution.Model;

public class Location {
    private int id;
    private String name;
    private String faName;
    private String state;
    private float latitute;
    private float longitude;

    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getLatitute() {
        return latitute;
    }

    public void setLatitute(float latitute) {
        this.latitute = latitute;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }


}
