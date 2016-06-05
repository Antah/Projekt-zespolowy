package pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class AddCommentInput implements Serializable {

    private AuthenticationData authenticationData;

    private String comment = "";

    private Integer photoId;

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

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
}
