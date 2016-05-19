package pl.edu.pw.ee.cosplay.rest.model.controller.login;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class LoginControllerInput implements Serializable {

    private String userName;
    private String password;

    public LoginControllerInput(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
