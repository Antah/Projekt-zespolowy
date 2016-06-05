package pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class AddCommentInput implements Serializable{

    private AuthenticationData authenticationData;

    private String comment = "";

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
