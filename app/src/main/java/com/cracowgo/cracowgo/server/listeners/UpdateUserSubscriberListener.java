package com.cracowgo.cracowgo.server.listeners;

/**
 * Created by Mohru on 18.03.2017.
 */

public interface UpdateUserSubscriberListener {

    void onUpdateUserCompleted();

    void onUpdateUserError();

    void onConnectionError();

    void onUnknownError();
}
