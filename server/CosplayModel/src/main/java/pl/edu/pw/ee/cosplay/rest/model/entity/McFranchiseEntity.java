package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michał on 2016-05-14.
 */
@Entity
@Table(name = "franchise", schema = "", catalog = "cosplay")
public class McFranchiseEntity implements Serializable {
    private Integer franchiseId;
    private String franchiseName;
    private Integer photoId;


    @Id
    @GeneratedValue
    @Column(name = "franchise_id")
    public Integer getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Integer franchiseId) {
        this.franchiseId = franchiseId;
    }

    @Basic
    @Column(name = "franchise_name")
    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }


    @Basic
    @Column(name = "photo_id")
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        McFranchiseEntity that = (McFranchiseEntity) o;

        if (franchiseId != null ? !franchiseId.equals(that.franchiseId) : that.franchiseId != null) return false;
        if (franchiseName != null ? !franchiseName.equals(that.franchiseName) : that.franchiseName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = franchiseId != null ? franchiseId.hashCode() : 0;
        result = 31 * result + (franchiseName != null ? franchiseName.hashCode() : 0);
        return result;
    }
}
