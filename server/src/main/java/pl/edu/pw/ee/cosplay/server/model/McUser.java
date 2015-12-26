package pl.edu.pw.ee.cosplay.server.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michał on 2015-12-24.
 */

@Entity
@Table(name = "users")
public class McUser implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login", length = 16, nullable = false, unique = true)
    private String login;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "status_id", nullable = false)
    private Long statusId = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "McUser{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}
