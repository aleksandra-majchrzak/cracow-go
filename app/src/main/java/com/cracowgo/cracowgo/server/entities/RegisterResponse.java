package com.cracowgo.cracowgo.server.entities;

/**
 * Created by Mohru on 18.03.2017.
 */

public class RegisterResponse {

    private Header headers;
    private User user;

    public RegisterResponse(Header headers, User user) {
        this.headers = headers;
        this.user = user;
    }

    public Header getHeaders() {
        return headers;
    }

    public User getUser() {
        return user;
    }
}
