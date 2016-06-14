package pl.edu.pw.ee.cosplay.rest.server.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Micha³ on 2016-06-12.
 */
@Entity
@Table(name = "user", schema = "", catalog = "cosplay")
public class McUserEntity {
    private String username;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String passwd;

    @Basic
    @Column(name = "passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        McUserEntity that = (McUserEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (passwd != null ? !passwd.equals(that.passwd) : that.passwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        return result;
    }

    private Collection<McCommentEntity> commentsByUsername;

    @OneToMany(cascade = {}, mappedBy = "userByUsername")
    public Collection<McCommentEntity> getCommentsByUsername() {
        return commentsByUsername;
    }

    public void setCommentsByUsername(Collection<McCommentEntity> commentsByUsername) {
        this.commentsByUsername = commentsByUsername;
    }

    private Collection<McObservationEntity> observationsByUsername;

    @OneToMany(cascade = {}, mappedBy = "userByObserved")
    public Collection<McObservationEntity> getObservationsByUsername() {
        return observationsByUsername;
    }

    public void setObservationsByUsername(Collection<McObservationEntity> observationsByUsername) {
        this.observationsByUsername = observationsByUsername;
    }

    private Collection<McObservationEntity> observationsByUsername_0;

    @OneToMany(cascade = {}, mappedBy = "userByObserver")
    public Collection<McObservationEntity> getObservationsByUsername_0() {
        return observationsByUsername_0;
    }

    public void setObservationsByUsername_0(Collection<McObservationEntity> observationsByUsername_0) {
        this.observationsByUsername_0 = observationsByUsername_0;
    }

    private Collection<McPhotoEntity> photosByUsername;

    @OneToMany(cascade = {}, mappedBy = "userByUsername")
    public Collection<McPhotoEntity> getPhotosByUsername() {
        return photosByUsername;
    }

    public void setPhotosByUsername(Collection<McPhotoEntity> photosByUsername) {
        this.photosByUsername = photosByUsername;
    }

    private Collection<McRatingEntity> ratingsByUsername;

    @OneToMany(cascade = {}, mappedBy = "userByUsername")
    public Collection<McRatingEntity> getRatingsByUsername() {
        return ratingsByUsername;
    }

    public void setRatingsByUsername(Collection<McRatingEntity> ratingsByUsername) {
        this.ratingsByUsername = ratingsByUsername;
    }

    private McBinaryPhotoEntity binaryPhotoByAvatarBinaryPhotoId;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "avatar_binary_photo_id", referencedColumnName = "binary_photo_id", nullable = true, insertable = true, updatable = true, table = "")
    public McBinaryPhotoEntity getBinaryPhotoByAvatarBinaryPhotoId() {
        return binaryPhotoByAvatarBinaryPhotoId;
    }

    public void setBinaryPhotoByAvatarBinaryPhotoId(McBinaryPhotoEntity binaryPhotoByAvatarBinaryPhotoId) {
        this.binaryPhotoByAvatarBinaryPhotoId = binaryPhotoByAvatarBinaryPhotoId;
    }
}
