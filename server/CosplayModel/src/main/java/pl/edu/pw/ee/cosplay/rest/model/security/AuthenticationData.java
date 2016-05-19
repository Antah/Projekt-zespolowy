package pl.edu.pw.ee.cosplay.rest.model.security;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class AuthenticationData implements Serializable {
    private String token;
    private String username;

    public AuthenticationData(String username, String token) {
        this.token = token;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
