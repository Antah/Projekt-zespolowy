package pl.edu.pw.ee.cosplay.rest.server.entity;

import pl.edu.pw.ee.cosplay.rest.model.controller.photos.RatingData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Micha³ on 2016-06-12.
 */
@Entity
@Table(name = "photo", schema = "", catalog = "cosplay")
public class McPhotoEntity {
    private Integer photoId;
    private Date uploadDate;
    private String description;
    private Collection<McCharacteerEntity> characteersByPhotoId;
    private Collection<McCommentEntity> commentsByPhotoId;
    private Collection<McFranchiseEntity> franchisesByPhotoId;
    private McUserEntity userByUsername;
    private McBinaryPhotoEntity binaryPhotoByPhotoBinaryPhotoId;
    private Collection<McRatingEntity> ratingsByPhotoId;

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
    @Temporal(TemporalType.TIMESTAMP)
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

    @OneToMany(cascade = {}, mappedBy = "photoByPhotoId")
    public Collection<McCharacteerEntity> getCharacteersByPhotoId() {
        return characteersByPhotoId;
    }

    public void setCharacteersByPhotoId(Collection<McCharacteerEntity> characteersByPhotoId) {
        this.characteersByPhotoId = characteersByPhotoId;
    }

    @OneToMany(cascade = {}, mappedBy = "photoByPhotoId")
    public Collection<McCommentEntity> getCommentsByPhotoId() {
        return commentsByPhotoId;
    }

    public void setCommentsByPhotoId(Collection<McCommentEntity> commentsByPhotoId) {
        this.commentsByPhotoId = commentsByPhotoId;
    }

    @OneToMany(cascade = {}, mappedBy = "photoByPhotoId")
    public Collection<McFranchiseEntity> getFranchisesByPhotoId() {
        return franchisesByPhotoId;
    }

    public void setFranchisesByPhotoId(Collection<McFranchiseEntity> franchisesByPhotoId) {
        this.franchisesByPhotoId = franchisesByPhotoId;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = true, insertable = true, updatable = true, table = "")
    public McUserEntity getUserByUsername() {
        return userByUsername;
    }

    public void setUserByUsername(McUserEntity userByUsername) {
        this.userByUsername = userByUsername;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "photo_binary_photo_id", referencedColumnName = "binary_photo_id", nullable = true, insertable = true, updatable = true, table = "")
    public McBinaryPhotoEntity getBinaryPhotoByPhotoBinaryPhotoId() {
        return binaryPhotoByPhotoBinaryPhotoId;
    }

    public void setBinaryPhotoByPhotoBinaryPhotoId(McBinaryPhotoEntity binaryPhotoByPhotoBinaryPhotoId) {
        this.binaryPhotoByPhotoBinaryPhotoId = binaryPhotoByPhotoBinaryPhotoId;
    }

    @OneToMany(cascade = {}, mappedBy = "photoByPhotoId")
    public Collection<McRatingEntity> getRatingsByPhotoId() {
        return ratingsByPhotoId;
    }

    public void setRatingsByPhotoId(Collection<McRatingEntity> ratingsByPhotoId) {
        this.ratingsByPhotoId = ratingsByPhotoId;
    }

    @Transient
    public static SimplePhotoData createPhotoDataFromPhotoEntity(SimplePhotoData photoData, McPhotoEntity photoEntity) {
        photoData.setPhotoId(photoEntity.getPhotoId());
        if (photoEntity.getUserByUsername().getBinaryPhotoByAvatarBinaryPhotoId() != null)
            photoData.setAvatarBinaryData(photoEntity.getUserByUsername().getBinaryPhotoByAvatarBinaryPhotoId().getBinaryData());
        photoData.setUsername(photoEntity.getUserByUsername().getUsername());
        photoData.setUploadDate(photoEntity.getUploadDate());
        photoData.setCommentsNumber(photoEntity.getCommentsByPhotoId().size());
        photoData.setPhotoBinaryData(photoEntity.getBinaryPhotoByPhotoBinaryPhotoId().getBinaryData());
        for (McFranchiseEntity franchiseEntity : photoEntity.getFranchisesByPhotoId()) {
            photoData.getFranchisesList().add(franchiseEntity.getFranchiseName());
        }
        for (McCharacteerEntity characteerEntity : photoEntity.getCharacteersByPhotoId()) {
            photoData.getCharactersList().add(characteerEntity.getCharacteerName());
        }
        RatingData ratingData = getRatingData(photoEntity);
        photoData.setRatingData(ratingData);
        return photoData;
    }

    @Transient
    public static RatingData getRatingData(McPhotoEntity photoEntity) {
        RatingData ratingData = new RatingData();
        for (McRatingEntity ratingEntity : photoEntity.getRatingsByPhotoId()) {
            ratingData.setSimilarityRate(ratingData.getSimilarityRate() + ratingEntity.getRatingSimilarity());
            ratingData.setQualityRate(ratingData.getQualityRate() + ratingEntity.getRatingQuality());
            ratingData.setArrangementRate(ratingData.getArrangementRate() + ratingEntity.getRatingArrangemnt());
        }
        if (photoEntity.getRatingsByPhotoId().size() > 0) {
            ratingData.setArrangementRate(ratingData.getArrangementRate() / photoEntity.getRatingsByPhotoId().size());
            ratingData.setQualityRate(ratingData.getQualityRate() / photoEntity.getRatingsByPhotoId().size());
            ratingData.setSimilarityRate(ratingData.getSimilarityRate() / photoEntity.getRatingsByPhotoId().size());
            ratingData.setGeneralRate
                    ((ratingData.getArrangementRate() + ratingData.getQualityRate() + ratingData.getSimilarityRate()) / 3);
        }
        return ratingData;
    }
}
