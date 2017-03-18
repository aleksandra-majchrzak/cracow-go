package com.cracowgo.cracowgo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohru on 18.03.2017.
 */

public class HeadersGenerator {

    public static Map<String, String> getHeaders(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREFERENCES, 0);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("Accept", "application/json");
        headers.put(Constants.accessToken, prefs.getString(Constants.accessToken, ""));
        headers.put(Constants.tokenType, prefs.getString(Constants.tokenType, ""));
        headers.put(Constants.client, prefs.getString(Constants.client, ""));
        headers.put(Constants.expiry, prefs.getString(Constants.expiry, ""));
        headers.put(Constants.uid, prefs.getString(Constants.uid, ""));

        return headers;
    }
}
