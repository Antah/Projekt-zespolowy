package pl.edu.pw.ee.cosplay.rest.model.controller.photos;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Micha� on 2016-06-03.
 */
public class CommentData implements Serializable {

    private String username;

    private Date commentDate;

    //mo�e by� null, je�eli u�ytkownik, kt�ry wstawi� zdj�cie nie ma awatara
    private byte[] avatarBinaryData = null;

    private String comment;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getAvatarBinaryData() {
        return avatarBinaryData;
    }

    public void setAvatarBinaryData(byte[] avatarBinaryData) {
        this.avatarBinaryData = avatarBinaryData;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
