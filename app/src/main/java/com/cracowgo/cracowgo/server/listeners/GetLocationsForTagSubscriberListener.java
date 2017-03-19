package com.cracowgo.cracowgo.server.listeners;

import com.cracowgo.cracowgo.server.entities.Location;

/**
 * Created by Mohru on 19.03.2017.
 */

public interface GetLocationsForTagSubscriberListener {
    void onGetLocationsForTagCompleted(Location[] locations);

    void onGetLocationsError();

    void onConnectionError();

    void onUnknownError();
}
