package com.plateer.ec1.payment.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class InicisUtil {

    private static final Gson gson;

    static {
        gson = new Gson();
    }

    public static Map<String, String> parseJsonToStringMap(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
    }

}
