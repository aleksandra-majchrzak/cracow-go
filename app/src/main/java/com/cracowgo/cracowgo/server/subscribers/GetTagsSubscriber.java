package com.cracowgo.cracowgo.server.subscribers;

import com.cracowgo.cracowgo.server.entities.Tag;
import com.cracowgo.cracowgo.server.listeners.GetTagsSubscriberListener;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Mohru on 18.03.2017.
 */

public class GetTagsSubscriber extends Subscriber<Tag[]> {

    GetTagsSubscriberListener mListener;

    public GetTagsSubscriber(GetTagsSubscriberListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {

            mListener.onGetTagsError();

        } else if (e instanceof IOException) {
            mListener.onConnectionError();
        } else {
            mListener.onUnknownError();
        }
    }

    @Override
    public void onNext(Tag[] tags) {
        mListener.onGetTagsCompleted(tags);
    }
}
