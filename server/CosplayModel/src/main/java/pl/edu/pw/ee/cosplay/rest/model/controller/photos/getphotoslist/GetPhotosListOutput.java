package pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist;

import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Micha³ on 2016-05-20.
 */
public class GetPhotosListOutput implements Serializable{

    private ArrayList<SimplePhotoData> simplePhotoDataList;
    private Boolean areThereNextPhotos;

    public ArrayList<SimplePhotoData> getSimplePhotoDataList() {
        return simplePhotoDataList;
    }

    public void setSimplePhotoDataList(ArrayList<SimplePhotoData> simplePhotoDataList) {
        this.simplePhotoDataList = simplePhotoDataList;
    }

    public Boolean getAreThereNextPhotos() {
        return areThereNextPhotos;
    }

    public void setAreThereNextPhotos(Boolean areThereNextPhotos) {
        this.areThereNextPhotos = areThereNextPhotos;
    }
}
