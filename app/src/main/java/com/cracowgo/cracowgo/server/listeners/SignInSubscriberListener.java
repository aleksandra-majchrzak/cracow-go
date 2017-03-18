package com.cracowgo.cracowgo.server.listeners;

import com.cracowgo.cracowgo.server.entities.RegisterResponse;

/**
 * Created by Mohru on 18.03.2017.
 */

public interface SignInSubscriberListener {

    void onSignInCompleted(RegisterResponse registerResponse);

    void onSignInError();

    void onConnectionError();

    void onUnknownError();
}
