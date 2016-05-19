package pl.edu.pw.ee.cosplay.rest.server.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.ee.cosplay.rest.model.entity.McBinaryPhotoEntity;
import pl.edu.pw.ee.cosplay.rest.model.entity.McObservationEntity;

/**
 * Created by Micha� on 2016-05-19.
 */
@Transactional
public interface ObservationDAO extends CrudRepository<McObservationEntity, Integer>{

}
