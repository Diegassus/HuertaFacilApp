package com.example.huertafacilapp.models.apoyo;

import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("type")
    private double type;

    @SerializedName("id")
    private Long id;

    @SerializedName("message")
    private Double message;

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private Long sunrise;

    @SerializedName("sunset")
    private Long sunset;

    @SerializedName("pod")
    private Character pod;

    public double getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public Double getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public Character getPod() {
        return pod;
    }
}