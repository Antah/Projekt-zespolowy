package pl.edu.pw.ee.cosplay.rest.model.controller.user;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-06-13.
 */
public class GetUserInput implements Serializable {
    private AuthenticationData authenticationData;
    private String username;

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
