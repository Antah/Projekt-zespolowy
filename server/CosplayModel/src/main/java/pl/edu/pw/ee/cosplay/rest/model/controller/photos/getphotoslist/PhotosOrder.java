package pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-20.
 */
public enum PhotosOrder implements Serializable{
    UPLOAD_DATE,
    UPLOAD_DATE_DESC,
    COMMENTS_NO,
    COMMENTS_NO_DESC,
    GENERAL_RATE,
    GENERAL_RATE_DESC,
    SIMILARITY_RATE,
    SIMILARITY_RATE_DESC,
    QUALITY_RATE,
    QUALITY_RATE_DESC,
    ARRANGEMENT_RATE,
    ARRANGEMENT_RATE_DESC
}
