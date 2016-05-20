package pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-20.
 */
public class AddPhotoOutput implements Serializable{

    private Integer addedPhotoId;

    public Integer getAddedPhotoId() {
        return addedPhotoId;
    }

    public void setAddedPhotoId(Integer addedPhotoId) {
        this.addedPhotoId = addedPhotoId;
    }
}
