package pl.edu.pw.ee.cosplay.rest.server.entity;

import javax.persistence.*;

/**
 * Created by Micha³ on 2016-06-12.
 */
@Entity
@Table(name = "franchise", schema = "", catalog = "cosplay")
public class McFranchiseEntity {
    private Integer franchiseId;
    private String franchiseName;
    private McPhotoEntity photoByPhotoId;

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

    @ManyToOne(cascade = {})
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id", nullable = true, insertable = true, updatable = true, table = "")
    public McPhotoEntity getPhotoByPhotoId() {
        return photoByPhotoId;
    }

    public void setPhotoByPhotoId(McPhotoEntity photoByPhotoId) {
        this.photoByPhotoId = photoByPhotoId;
    }
}
