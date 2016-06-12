package pl.edu.pw.ee.cosplay.rest.server.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Micha³ on 2016-06-12.
 */
@Entity
@Table(name = "binary_photo", schema = "", catalog = "cosplay")
public class McBinaryPhotoEntity {
    private Integer binaryPhotoId;
    private byte[] binaryData;
    private Collection<McPhotoEntity> photosByBinaryPhotoId;
    private Collection<McUserEntity> usersByBinaryPhotoId;

    @Id
    @GeneratedValue
    @Column(name = "binary_photo_id")
    public Integer getBinaryPhotoId() {
        return binaryPhotoId;
    }

    public void setBinaryPhotoId(Integer binaryPhotoId) {
        this.binaryPhotoId = binaryPhotoId;
    }

    @Basic
    @Column(name = "binary_data")
    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        McBinaryPhotoEntity that = (McBinaryPhotoEntity) o;

        if (binaryPhotoId != null ? !binaryPhotoId.equals(that.binaryPhotoId) : that.binaryPhotoId != null)
            return false;
        if (!Arrays.equals(binaryData, that.binaryData)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = binaryPhotoId != null ? binaryPhotoId.hashCode() : 0;
        result = 31 * result + (binaryData != null ? Arrays.hashCode(binaryData) : 0);
        return result;
    }

    @OneToMany(cascade = {}, mappedBy = "binaryPhotoByPhotoBinaryPhotoId")
    public Collection<McPhotoEntity> getPhotosByBinaryPhotoId() {
        return photosByBinaryPhotoId;
    }

    public void setPhotosByBinaryPhotoId(Collection<McPhotoEntity> photosByBinaryPhotoId) {
        this.photosByBinaryPhotoId = photosByBinaryPhotoId;
    }

    @OneToMany(cascade = {}, mappedBy = "binaryPhotoByAvatarBinaryPhotoId")
    public Collection<McUserEntity> getUsersByBinaryPhotoId() {
        return usersByBinaryPhotoId;
    }

    public void setUsersByBinaryPhotoId(Collection<McUserEntity> usersByBinaryPhotoId) {
        this.usersByBinaryPhotoId = usersByBinaryPhotoId;
    }
}
