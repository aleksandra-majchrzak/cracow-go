package com.cracowgo.cracowgo.server.subscribers;

import com.cracowgo.cracowgo.server.entities.Location;
import com.cracowgo.cracowgo.server.listeners.GetLocationsForTagSubscriberListener;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Mohru on 19.03.2017.
 */

public class GetLocationsForTagSubscriber extends Subscriber<Location[]> {

    private GetLocationsForTagSubscriberListener mListener;

    public GetLocationsForTagSubscriber(GetLocationsForTagSubscriberListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {

            mListener.onGetLocationsError();

        } else if (e instanceof IOException) {
            mListener.onConnectionError();
        } else {
            mListener.onUnknownError();
        }
    }

    @Override
    public void onNext(Location[] locations) {
        mListener.onGetLocationsForTagCompleted(locations);
    }
}
