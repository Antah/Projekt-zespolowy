package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michał on 2016-05-14.
 */
@Entity
@Table(name = "user", schema = "", catalog = "cosplay")
public class McUserEntity implements Serializable {
    private String username;
    private String passwd;
    private Integer avatarBinaryPhotoId;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "passwd")
    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(final String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "avatar_binary_photo_id")
    public Integer getAvatarBinaryPhotoId() {
        return avatarBinaryPhotoId;
    }

    public void setAvatarBinaryPhotoId(Integer avatarBinaryPhotoId) {
        this.avatarBinaryPhotoId = avatarBinaryPhotoId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final McUserEntity that = (McUserEntity) o;

        if (this.username != null ? !this.username.equals(that.username) : that.username != null) return false;
        if (this.passwd != null ? !this.passwd.equals(that.passwd) : that.passwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.username != null ? this.username.hashCode() : 0;
        result = 31 * result + (this.passwd != null ? this.passwd.hashCode() : 0);
        return result;
    }
}
