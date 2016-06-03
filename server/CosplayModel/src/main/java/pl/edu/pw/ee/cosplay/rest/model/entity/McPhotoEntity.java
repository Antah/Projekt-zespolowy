package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Michał on 2016-05-14.
 */
@Entity
@Table(name = "photo", schema = "", catalog = "cosplay")
public class McPhotoEntity implements Serializable {
    private Integer photoId;
    private Date uploadDate;
    private String description;
    private Integer photoBinaryPhotoId;
    private String username;



    @Id
    @GeneratedValue
    @Column(name = "photo_id")
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "upload_date")
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "photo_binary_photo_id")
    public Integer getPhotoBinaryPhotoId() {
        return photoBinaryPhotoId;
    }

    public void setPhotoBinaryPhotoId(Integer photoBinaryPhotoId) {
        this.photoBinaryPhotoId = photoBinaryPhotoId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        McPhotoEntity that = (McPhotoEntity) o;

        if (photoId != null ? !photoId.equals(that.photoId) : that.photoId != null) return false;
        if (uploadDate != null ? !uploadDate.equals(that.uploadDate) : that.uploadDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
