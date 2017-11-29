package com.itheima.takeout.model.net.bean;

/**
 * 骑手坐标
 */
public class Location {
    private String longitude;
    private String latitude;

    public Location() {
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
