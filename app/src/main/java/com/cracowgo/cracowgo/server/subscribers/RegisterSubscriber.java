package com.cracowgo.cracowgo.server.subscribers;

import com.cracowgo.cracowgo.server.entities.Header;
import com.cracowgo.cracowgo.server.entities.RegisterResponse;
import com.cracowgo.cracowgo.server.entities.User;
import com.cracowgo.cracowgo.server.listeners.RegisterSubscriberListener;
import com.cracowgo.cracowgo.utils.Constants;
import com.cracowgo.cracowgo.utils.HTTPCodes;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Mohru on 18.03.2017.
 */

public class RegisterSubscriber extends Subscriber<retrofit2.Response<ResponseBody>> {

    private User mUser;
    private RegisterSubscriberListener mListener;

    public RegisterSubscriber(User user, RegisterSubscriberListener listener) {
        this.mUser = user;
        this.mListener = listener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof HttpException) {

            mListener.onRegistrationError();

        } else if (e instanceof IOException) {
            mListener.onConnectionError();
        } else {
            mListener.onUnknownError();
        }

    }

    @Override
    public void onNext(Response<ResponseBody> response) {

        if (response.errorBody() == null) {

            Headers headers = response.headers();
            Header mHeaders = new Header(headers.get(Constants.accessToken),
                    headers.get(Constants.tokenType),
                    headers.get(Constants.client),
                    headers.get(Constants.expiry),
                    headers.get(Constants.uid));

            ResponseBody responseBody = response.body();

            JsonObject json;
            int id = 0;
            try {

                json = new JsonParser().parse(responseBody.string()).getAsJsonObject();
                id = json.get("data").getAsJsonObject().get("id").getAsInt();

            } catch (IOException e) {
                e.printStackTrace();
            }

            mUser.setId(id);

            RegisterResponse regResponse = new RegisterResponse(mHeaders, mUser);
            mListener.onRegistrationCompleted(regResponse);
        } else {
            int errorCode = response.code();

            if (HTTPCodes.fromInt(errorCode) == HTTPCodes.UNPROCESSABLE_ENTIY) {

                mListener.onRegistrationError();

            } else {
                mListener.onUnknownError();
            }

        }
    }
}
