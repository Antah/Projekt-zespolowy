package pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-06-03.
 */
public class GetPhotoInput implements Serializable {
    private Integer photoId;

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
}
