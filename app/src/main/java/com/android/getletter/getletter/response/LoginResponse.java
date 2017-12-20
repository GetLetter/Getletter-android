package com.android.getletter.getletter.response;

import java.lang.reflect.Array;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by adrienzaganelli on 20/12/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    public Array results;

    public Array getResults() {
        return results;
    }

    public void setResults(Array results) {
        this.results = results;
    }
}
