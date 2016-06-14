package pl.edu.pw.ee.cosplay.rest.server.entity;

import javax.persistence.*;

/**
 * Created by Micha³ on 2016-06-12.
 */
@Entity
@Table(name = "observation", schema = "", catalog = "cosplay")
public class McObservationEntity {
    private Integer observationId;
    private McUserEntity userByObserved;
    private McUserEntity userByObserver;

    @Id
    @GeneratedValue
    @Column(name = "observation_id")
    public Integer getObservationId() {
        return observationId;
    }

    public void setObservationId(Integer observationId) {
        this.observationId = observationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        McObservationEntity that = (McObservationEntity) o;

        if (observationId != null ? !observationId.equals(that.observationId) : that.observationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = observationId != null ? observationId.hashCode() : 0;
        return result;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "observed", referencedColumnName = "username", nullable = true, insertable = true, updatable = true, table = "")
    public McUserEntity getUserByObserved() {
        return userByObserved;
    }

    public void setUserByObserved(McUserEntity userByObserved) {
        this.userByObserved = userByObserved;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "observer", referencedColumnName = "username", nullable = true, insertable = true, updatable = true, table = "")
    public McUserEntity getUserByObserver() {
        return userByObserver;
    }

    public void setUserByObserver(McUserEntity userByObserver) {
        this.userByObserver = userByObserver;
    }
}
