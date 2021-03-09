package com.example.jangbogo.util;

import java.util.Map;

public class DjangoAuth {
    static public String getAuthToken(Map<String, String> headers) {
        return headers.get("authorization").split(" ")[1];
    }

    static public String getUser(Map<String, String> headers) {
        return headers.get("authorization").split(" ")[1];
    }

    static public void showAllHeaders(Map<String, String> headers){
        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });
    }
}
