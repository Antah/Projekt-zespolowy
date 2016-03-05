package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Mapowanie tabeli status z mysql. Stara dobra Java. Implementuje Serializable,
 * abysmy mogli sobie zserializować obiekt i przesłać do klienta na Androidzie
 * <p>
 * Jako, że nazwy naszych tabelek mogą być zyt ogólne dobrą praktyką jest dodanie przedrostka
 * Mc* od Model cosplay.
 */
@Entity
@Table(name = "status")
public class McStatus implements Serializable {

    @Id
    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "name", nullable = false, length = 16)
    private String name;

    public Long getStatusId() {
        return this.statusId;
    }

    public void setStatusId(final Long statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "McStatus{" +
                "statusId=" + this.statusId +
                ", name='" + this.name + '\'' +
                '}';
    }
}
