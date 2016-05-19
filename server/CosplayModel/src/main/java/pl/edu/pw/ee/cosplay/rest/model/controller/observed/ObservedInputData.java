package pl.edu.pw.ee.cosplay.rest.model.controller.observed;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class ObservedInputData implements Serializable{

    private AuthenticationData authenticationData;

    private String userName;

    public ObservedInputData(AuthenticationData authenticationData, String userName) {
        this.authenticationData = authenticationData;
        this.userName = userName;
    }

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }

    public String getUserName() {
        return userName;
    }
}
