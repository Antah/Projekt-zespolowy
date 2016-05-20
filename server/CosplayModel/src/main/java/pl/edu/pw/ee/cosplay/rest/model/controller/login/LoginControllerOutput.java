package pl.edu.pw.ee.cosplay.rest.model.controller.login;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUserEntity;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class LoginControllerOutput implements Serializable{

    private AuthenticationData data;

    public AuthenticationData getAuthenticationData() {
        return data;
    }

    public void setAuthenticationData(AuthenticationData data) {
        this.data = data;
    }
}
