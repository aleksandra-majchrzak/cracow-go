package com.cracowgo.cracowgo.server.subscribers;

import com.cracowgo.cracowgo.server.entities.Location;
import com.cracowgo.cracowgo.server.listeners.GetAllNearbyLocationsSubscriberListener;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Mohru on 19.03.2017.
 */

public class GetAllNearbyLocationsSubscriber extends Subscriber<Location[]> {

    private GetAllNearbyLocationsSubscriberListener mListener;

    public GetAllNearbyLocationsSubscriber(GetAllNearbyLocationsSubscriberListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            mListener.onGetAllNearbyLocationsError();

        } else if (e instanceof IOException) {
            mListener.onConnectionError();
        } else {
            mListener.onUnknownError();
        }
    }

    @Override
    public void onNext(Location[] locations) {
        mListener.onGetAllNearbyLocationsCompleted(locations);
    }
}
