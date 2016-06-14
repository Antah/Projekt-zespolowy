package pl.edu.pw.ee.cosplay.rest.model.controller.photos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Micha³ on 2016-05-20.
 */
public class SimplePhotoData implements Serializable {

    private String username;

    private HashSet<String> franchisesList = new HashSet<>();

    private HashSet<String> charactersList = new HashSet<>();

    private Integer commentsNumber;

    private RatingData ratingData;

    private Integer photoId;

    private byte[] photoBinaryData;

    //mo¿e byæ null, je¿eli u¿ytkownik, który wstawi³ zdjêcie nie ma awataru
    private byte[] avatarBinaryData = null;

    private Date uploadDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashSet<String> getFranchisesList() {
        return franchisesList;
    }

    public void setFranchisesList(HashSet<String> franchisesList) {
        this.franchisesList = franchisesList;
    }

    public HashSet<String> getCharactersList() {
        return charactersList;
    }

    public void setCharactersList(HashSet<String> charactersList) {
        this.charactersList = charactersList;
    }

    public Integer getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Integer commentsNumber) {
        this.commentsNumber = commentsNumber;
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

    public byte[] getPhotoBinaryData() {
        return photoBinaryData;
    }

    public void setPhotoBinaryData(byte[] photoBinaryData) {
        this.photoBinaryData = photoBinaryData;
    }

    public byte[] getAvatarBinaryData() {
        return avatarBinaryData;
    }

    public void setAvatarBinaryData(byte[] avatarBinaryData) {
        this.avatarBinaryData = avatarBinaryData;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
