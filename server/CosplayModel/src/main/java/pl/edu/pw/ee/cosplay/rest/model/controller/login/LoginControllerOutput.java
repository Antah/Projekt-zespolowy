package pl.edu.pw.ee.cosplay.rest.model.controller.login;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUserEntity;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class LoginControllerOutput implements Serializable{

    private AuthenticationData data;

    private McUserEntity user;

    public LoginControllerOutput(AuthenticationData data, McUserEntity user) {
        this.data = data;
        this.user = user;
    }

    public AuthenticationData getAuthorizationData() {
        return data;
    }

    public McUserEntity getUser() {
        return user;
    }
}
