package com.example.huertafacilapp.models.apoyo;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    private double speed;

    @SerializedName("deg")
    private double deg;

    @SerializedName("gust")
    private Double gust;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

    public Double getGust() {
        return gust;
    }
}