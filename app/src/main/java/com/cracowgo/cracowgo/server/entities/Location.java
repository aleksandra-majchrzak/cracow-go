package com.cracowgo.cracowgo.server.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mohru on 19.03.2017.
 */

public class Location implements Parcelable {
    int id;
    String name;
    String description;
    double latitude;
    double longitude;
    Tag[] tags;

    public Location() {
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public Tag[] getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public Location(int id, String name, String description, double latitude, double longitude, Tag[] tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = tags;
    }

    protected Location(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
