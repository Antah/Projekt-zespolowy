package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Mapowanie tabeli users z mysql. Stara dobra Java. Implementuje Serializable,
 * abysmy mogli sobie zserializować obiekt i przesłać do klienta na Androidzie
 * <p/>
 * Jako, że nazwy naszych tabelek mogą być zyt ogólne dobrą praktyką jest dodanie przedrostka
 * Mc* od Model cosplay.
 * <p/>
 * Klasa wygląda na dość długą, ale pamiętajcie, że setery/gettery oraz toString możemy generować automatycznie!
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

