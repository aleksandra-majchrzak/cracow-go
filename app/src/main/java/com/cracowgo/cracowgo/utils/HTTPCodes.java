package com.cracowgo.cracowgo.utils;

/**
 * Created by Mohru on 18.03.2017.
 */

public enum HTTPCodes {
    UNPROCESSABLE_ENTIY(422), UNKNOWN(0);

    private int value;

    HTTPCodes(int value) {
        this.value = value;
    }

    public static HTTPCodes fromInt(int code) {
        switch (code) {
            case 422:
                return UNPROCESSABLE_ENTIY;
            default:
                return UNKNOWN;
        }
    }
}
