package pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class AddPhotoInput implements Serializable{

    private AuthenticationData authenticationData;

    private HashSet<String> franchisesList;

    private HashSet<String> charactersList;

    private String photoDescription;

    private byte[] photoBinaryData;

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }

    public void setAuthenticationData(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
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

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public byte[] getPhotoBinaryData() {
        return photoBinaryData;
    }

    public void setPhotoBinaryData(byte[] photoBinaryData) {
        this.photoBinaryData = photoBinaryData;
    }
}
