package pl.edu.pw.ee.cosplay.rest.server.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.ee.cosplay.rest.model.entity.McPhotoEntity;
import pl.edu.pw.ee.cosplay.rest.model.entity.McRatingEntity;

/**
 * Created by Micha³ on 2016-05-19.
 */
@Transactional
public interface RatingDAO extends CrudRepository<McRatingEntity, Integer>{

}
