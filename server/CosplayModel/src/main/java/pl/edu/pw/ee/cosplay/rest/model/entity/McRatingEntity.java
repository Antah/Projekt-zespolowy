package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michał on 2016-05-14.
 */
@Entity
@Table(name = "rating", schema = "", catalog = "cosplay")
public class McRatingEntity implements Serializable {
    private Integer ratingId;
    private Integer ratingSimilarity;
    private Integer ratingQuality;
    private Integer ratingArrangemnt;
    private Integer photoId;
    private String username;



    @Id
    @Column(name = "rating_id")
    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    @Basic
    @Column(name = "rating_similarity")
    public Integer getRatingSimilarity() {
        return ratingSimilarity;
    }

    public void setRatingSimilarity(Integer ratingSimilarity) {
        this.ratingSimilarity = ratingSimilarity;
    }

    @Basic
    @Column(name = "rating_quality")
    public Integer getRatingQuality() {
        return ratingQuality;
    }

    public void setRatingQuality(Integer ratingQuality) {
        this.ratingQuality = ratingQuality;
    }

    @Basic
    @Column(name = "rating_arrangemnt")
    public Integer getRatingArrangemnt() {
        return ratingArrangemnt;
    }

    public void setRatingArrangemnt(Integer ratingArrangemnt) {
        this.ratingArrangemnt = ratingArrangemnt;
    }

    @Basic
    @Column(name = "photo_id")
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
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

        McRatingEntity that = (McRatingEntity) o;

        if (ratingId != null ? !ratingId.equals(that.ratingId) : that.ratingId != null) return false;
        if (ratingSimilarity != null ? !ratingSimilarity.equals(that.ratingSimilarity) : that.ratingSimilarity != null)
            return false;
        if (ratingQuality != null ? !ratingQuality.equals(that.ratingQuality) : that.ratingQuality != null)
            return false;
        if (ratingArrangemnt != null ? !ratingArrangemnt.equals(that.ratingArrangemnt) : that.ratingArrangemnt != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ratingId != null ? ratingId.hashCode() : 0;
        result = 31 * result + (ratingSimilarity != null ? ratingSimilarity.hashCode() : 0);
        result = 31 * result + (ratingQuality != null ? ratingQuality.hashCode() : 0);
        result = 31 * result + (ratingArrangemnt != null ? ratingArrangemnt.hashCode() : 0);
        return result;
    }
}
