package com.android.getletter.getletter.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by adrienzaganelli on 20/12/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    public List results;

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }

    LoginResponse() {

    }
}
