package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Michał on 2016-05-14.
 */
@Entity
@Table(name = "characteer", schema = "", catalog = "cosplay")
public class McCharacteerEntity implements Serializable {
    private Integer characteerId;
    private String characteerName;
    private Integer photoId;

    @Id
    @Column(name = "characteer_id")
    public Integer getCharacteerId() {
        return characteerId;
    }

    public void setCharacteerId(Integer characteerId) {
        this.characteerId = characteerId;
    }

    @Basic
    @Column(name = "characteer_name")
    public String getCharacteerName() {
        return characteerName;
    }

    public void setCharacteerName(String characteerName) {
        this.characteerName = characteerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        McCharacteerEntity that = (McCharacteerEntity) o;

        if (characteerId != null ? !characteerId.equals(that.characteerId) : that.characteerId != null) return false;
        if (characteerName != null ? !characteerName.equals(that.characteerName) : that.characteerName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = characteerId != null ? characteerId.hashCode() : 0;
        result = 31 * result + (characteerName != null ? characteerName.hashCode() : 0);
        return result;
    }

    @Column(name = "photo_id")
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
}
