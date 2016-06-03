package pl.edu.pw.ee.cosplay.rest.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michał on 2016-05-14.
 */
@Entity
@Table(name = "observation", schema = "", catalog = "cosplay")
public class McObservationEntity implements Serializable {
    private Integer observationId;
    private String observed;
    private String observer;

    @Id
    @GeneratedValue
    @Column(name = "observation_id")
    public Integer getObservationId() {
        return observationId;
    }

    public void setObservationId(Integer observationId) {
        this.observationId = observationId;
    }

    @Column(name = "observed")
    public String getObserved() {
        return this.observed;
    }

    public void setObserved(final String observed) {
        this.observed = observed;
    }


    @Column(name = "observer")
    public String getObserver() {
        return this.observer;
    }

    public void setObserver(final String observer) {
        this.observer = observer;
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
        return observationId != null ? observationId.hashCode() : 0;
    }
}
