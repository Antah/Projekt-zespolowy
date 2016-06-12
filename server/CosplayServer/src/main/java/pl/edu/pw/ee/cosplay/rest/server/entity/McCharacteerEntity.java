package pl.edu.pw.ee.cosplay.rest.server.entity;

import javax.persistence.*;


/**
 * Created by Micha³ on 2016-06-12.
 */
@Entity
@Table(name = "characteer", schema = "", catalog = "cosplay")
public class McCharacteerEntity {
    private Integer characteerId;
    private String characteerName;
    private McPhotoEntity photoByPhotoId;

    @Id
    @GeneratedValue
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

    @ManyToOne(cascade = {})
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id", nullable = true, insertable = true, updatable = true, table = "")
    public McPhotoEntity getPhotoByPhotoId() {
        return photoByPhotoId;
    }

    public void setPhotoByPhotoId(McPhotoEntity photoByPhotoId) {
        this.photoByPhotoId = photoByPhotoId;
    }
}
