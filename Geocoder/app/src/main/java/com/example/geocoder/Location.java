package com.example.geocoder;

public class Location
{
    private double latitude_pick;
    private double longitude_pick;
    private double latitude_drop;
    private double longitude_drop;

    public Location() {
    }

    public Location(double latitude_pick, double longitude_pick, double latitude_drop, double longitude_drop) {
        this.latitude_pick = latitude_pick;
        this.longitude_pick = longitude_pick;
        this.latitude_drop = latitude_drop;
        this.longitude_drop = longitude_drop;
    }

    public double getLatitude_pick() {
        return latitude_pick;
    }

    public void setLatitude_pick(double latitude_pick) {
        this.latitude_pick = latitude_pick;
    }

    public double getLongitude_pick() {
        return longitude_pick;
    }

    public void setLongitude_pick(double longitude_pick) {
        this.longitude_pick = longitude_pick;
    }

    public double getLatitude_drop() {
        return latitude_drop;
    }

    public void setLatitude_drop(double latitude_drop) {
        this.latitude_drop = latitude_drop;
    }

    public double getLongitude_drop() {
        return longitude_drop;
    }

    public void setLongitude_drop(double longitude_drop) {
        this.longitude_drop = longitude_drop;
    }
}
