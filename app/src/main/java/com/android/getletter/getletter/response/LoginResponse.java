package com.android.getletter.getletter.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by adrienzaganelli on 20/12/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    private String auth_token;

    LoginResponse() {}
}
