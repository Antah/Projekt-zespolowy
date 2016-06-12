package pl.edu.pw.ee.cosplay.rest.server.entity;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Micha³ on 2016-06-12.
 */
@Entity
@Table(name = "comment", schema = "", catalog = "cosplay")
public class McCommentEntity {
    private Integer commentId;
    private String content;
    private Date commentDate;
    private McPhotoEntity photoByPhotoId;
    private McUserEntity userByUsername;

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_date")
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        McCommentEntity that = (McCommentEntity) o;

        if (commentId != null ? !commentId.equals(that.commentId) : that.commentId != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        return result;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id", nullable = true, insertable = true, updatable = true, table = "")
    public McPhotoEntity getPhotoByPhotoId() {
        return photoByPhotoId;
    }

    public void setPhotoByPhotoId(McPhotoEntity photoByPhotoId) {
        this.photoByPhotoId = photoByPhotoId;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = true, insertable = true, updatable = true, table = "")
    public McUserEntity getUserByUsername() {
        return userByUsername;
    }

    public void setUserByUsername(McUserEntity userByUsername) {
        this.userByUsername = userByUsername;
    }
}
