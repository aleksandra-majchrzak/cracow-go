package com.cracowgo.cracowgo.server;

import com.cracowgo.cracowgo.server.entities.User;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Mohru on 18.03.2017.
 */

public interface CracowGoApi {

    @POST("/auth")
    Observable<Response<ResponseBody>> register(@Body User user);

    @POST("/auth/sign_in")
    Observable<Response<ResponseBody>> signIn(@Body User user);
}
