package com.cracowgo.cracowgo.server;

import com.cracowgo.cracowgo.server.entities.User;
import com.cracowgo.cracowgo.server.listeners.GetTagsSubscriberListener;
import com.cracowgo.cracowgo.server.listeners.RegisterSubscriberListener;
import com.cracowgo.cracowgo.server.listeners.SignInSubscriberListener;
import com.cracowgo.cracowgo.server.listeners.UpdateUserSubscriberListener;
import com.cracowgo.cracowgo.server.subscribers.GetTagsSubscriber;
import com.cracowgo.cracowgo.server.subscribers.RegisterSubscriber;
import com.cracowgo.cracowgo.server.subscribers.SignInSubscriber;
import com.cracowgo.cracowgo.server.subscribers.UpdateUserSubscriber;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mohru on 18.03.2017.
 */

public class CracowGoService {

    private static final String baseURL = "https://mysterious-meadow-35009.herokuapp.com/";

    private static CracowGoService service;
    private static CracowGoApi mApi;

    public static CracowGoService getInstance() {
        if (service == null) {
            service = new CracowGoService();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            mApi = retrofit.create(CracowGoApi.class);
        }

        return service;
    }

    public Subscription register(User user, RegisterSubscriberListener listener) {
        return mApi.register(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RegisterSubscriber(user, listener));
    }

    public Subscription signIn(User user, SignInSubscriberListener listener) {
        return mApi.signIn(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SignInSubscriber(user, listener));
    }

    public Subscription updateUser(User user, UpdateUserSubscriberListener listener, Map<String, String> headers) {
        return mApi.updateUser(headers, user.getId(), user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new UpdateUserSubscriber(user, listener));
    }

    public Subscription getTags(Map<String, String> headers, GetTagsSubscriberListener listener) {
        return mApi.getTags(headers)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GetTagsSubscriber(listener));
    }

}
