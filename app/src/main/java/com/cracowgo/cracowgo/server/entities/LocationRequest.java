package com.cracowgo.cracowgo.server.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohru on 19.03.2017.
 */

public class LocationRequest {
    @SerializedName("tag_id")
    int tagId;
    double latitude;
    double longitude;

    public LocationRequest(int tagId, double latitude, double longitude) {
        this.tagId = tagId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getTagId() {
        return tagId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
