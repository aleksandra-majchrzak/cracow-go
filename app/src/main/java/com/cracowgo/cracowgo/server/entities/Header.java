package com.cracowgo.cracowgo.server.entities;

/**
 * Created by Mohru on 18.03.2017.
 */

public class Header {
    private String accessToken;
    private String tokenType;
    private String client;
    private String expiry;
    private String uid;

    public Header(String accessToken, String tokenType, String client, String expiry, String uid) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.client = client;
        this.expiry = expiry;
        this.uid = uid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getClient() {
        return client;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getUid() {
        return uid;
    }
}
