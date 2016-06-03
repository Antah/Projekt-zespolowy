package pl.edu.pw.ee.cosplay.rest.model.controller.login;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class LoginControllerInput implements Serializable {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
