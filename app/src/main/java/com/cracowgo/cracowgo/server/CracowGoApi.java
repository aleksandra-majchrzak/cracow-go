package com.cracowgo.cracowgo.server;

import com.cracowgo.cracowgo.server.entities.Location;
import com.cracowgo.cracowgo.server.entities.LocationRequest;
import com.cracowgo.cracowgo.server.entities.Tag;
import com.cracowgo.cracowgo.server.entities.User;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Mohru on 18.03.2017.
 */

public interface CracowGoApi {

    @POST("/auth")
    Observable<Response<ResponseBody>> register(@Body User user);

    @POST("/auth/sign_in")
    Observable<Response<ResponseBody>> signIn(@Body User user);

    @PUT("/users/{id}")
    Observable<ResponseBody> updateUser(@HeaderMap Map<String, String> headers, @Path("id") int userId, @Body User user);

    @GET("/tags")
    Observable<Tag[]> getTags(@HeaderMap Map<String, String> headers);

    @POST("/paths/generate_path")
    Observable<Location[]> getLocationsForTag(@HeaderMap Map<String, String> headers, @Body LocationRequest requestBody);
}
