package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michał on 2015-12-24.
 */

@Entity
@Table(name = "users")
public class McUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login", length = 16, nullable = false, unique = true)
    private String login;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "status_id", nullable = false)
    private Long statusId = 1L;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Long getStatusId() {
        return this.statusId;
    }

    public void setStatusId(final Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "McUser{" +
                "userId=" + this.userId +
                ", login='" + this.login + '\'' +
                ", password='" + this.password + '\'' +
                ", statusId=" + this.statusId +
                '}';
    }
}

