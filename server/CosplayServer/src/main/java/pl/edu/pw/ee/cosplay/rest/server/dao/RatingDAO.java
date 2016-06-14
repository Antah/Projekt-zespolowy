package pl.edu.pw.ee.cosplay.rest.server.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.ee.cosplay.rest.server.entity.McRatingEntity;

/**
 * Created by Micha� on 2016-05-19.
 */
@Transactional
public interface RatingDAO extends CrudRepository<McRatingEntity, Integer> {

    @Query("SELECT r FROM McRatingEntity r JOIN r.photoByPhotoId p JOIN r.userByUsername u WHERE u.username = :username AND p.photoId = :photoId")
    McRatingEntity getRatingByPhotoAndUser(@Param("username") String username, @Param("photoId") Integer photoId);

}
