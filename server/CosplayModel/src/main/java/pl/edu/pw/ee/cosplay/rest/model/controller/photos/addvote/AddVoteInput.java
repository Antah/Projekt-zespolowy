package pl.edu.pw.ee.cosplay.rest.model.controller.photos.addvote;

import pl.edu.pw.ee.cosplay.rest.model.controller.photos.RatingData;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.Serializable;

/**
 * Created by Micha� on 2016-06-03.
 */
public class AddVoteInput implements Serializable {

    private AuthenticationData authenticationData;

    private RatingData ratingData; //W rating data General rate b�dzie nullem, bo nie jest potrzebne

    private Integer photoId; //id ocenianego zdj�cia

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
    }

    public RatingData getRatingData() {
        return ratingData;
    }

    public void setRatingData(RatingData ratingData) {
        this.ratingData = ratingData;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
}
