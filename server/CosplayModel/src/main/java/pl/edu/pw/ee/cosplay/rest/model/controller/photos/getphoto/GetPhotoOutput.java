package pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto;

import pl.edu.pw.ee.cosplay.rest.model.controller.photos.CommentData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;

import java.util.ArrayList;

/**
 * Created by Micha³ on 2016-06-03.
 */

//Te same dane co w simple photo data, ale nie trzeba liczyæ iloœci komentarzy (commentsNumber)
public class GetPhotoOutput extends SimplePhotoData{

    private String description;

    private ArrayList<CommentData> comments = new ArrayList<>();

    public ArrayList<CommentData> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentData> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
