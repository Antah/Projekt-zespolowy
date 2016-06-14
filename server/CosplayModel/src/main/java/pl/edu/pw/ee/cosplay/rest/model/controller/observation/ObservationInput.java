package pl.edu.pw.ee.cosplay.rest.model.controller.observation;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-06-13.
 */
public class ObservationInput implements Serializable {
    private AuthenticationData authenticationData;
    private Boolean unObserve = false;
    private String username;

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
    }

    public Boolean getUnObserve() {
        return unObserve;
    }

    public void setUnObserve(Boolean unObserve) {
        this.unObserve = unObserve;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
