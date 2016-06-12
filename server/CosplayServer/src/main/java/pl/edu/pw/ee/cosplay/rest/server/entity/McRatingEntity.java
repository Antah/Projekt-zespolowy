package pl.edu.pw.ee.cosplay.rest.server.entity;

import javax.persistence.*;

/**
 * Created by Micha³ on 2016-06-12.
 */
@Entity
@Table(name = "rating", schema = "", catalog = "cosplay")
public class McRatingEntity {
    private Integer ratingId;
    private Integer ratingSimilarity;
    private Integer ratingQuality;
    private Integer ratingArrangemnt;
    private McPhotoEntity photoByPhotoId;
    private McUserEntity userByUsername;

    @Id
    @GeneratedValue
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
