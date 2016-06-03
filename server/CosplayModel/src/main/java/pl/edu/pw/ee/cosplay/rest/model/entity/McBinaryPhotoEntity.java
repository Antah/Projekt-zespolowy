package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Michał on 2016-05-14.
 */
@Entity
@Table(name = "binary_photo", schema = "", catalog = "cosplay")
public class McBinaryPhotoEntity implements Serializable {
    private Integer binaryPhotoId;
    private byte[] binaryData;

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
        result = 31 * result + Arrays.hashCode(binaryData);
        return result;
    }
}
