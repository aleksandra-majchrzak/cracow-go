package com.cracowgo.cracowgo.server.subscribers;

import com.cracowgo.cracowgo.server.entities.User;
import com.cracowgo.cracowgo.server.listeners.UpdateUserSubscriberListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Mohru on 18.03.2017.
 */

public class UpdateUserSubscriber extends Subscriber<ResponseBody> {

    private User mUser;
    private UpdateUserSubscriberListener mListener;

    public UpdateUserSubscriber(User user, UpdateUserSubscriberListener listener) {
        this.mUser = user;
        this.mListener = listener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {

            mListener.onUpdateUserError();

        } else if (e instanceof IOException) {
            mListener.onConnectionError();
        } else {
            mListener.onUnknownError();
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        mListener.onUpdateUserCompleted();
    }
}
