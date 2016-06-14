package pl.edu.pw.ee.cosplay.rest.model.controller.login;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-06-14.
 */
public class RegisterInput implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
