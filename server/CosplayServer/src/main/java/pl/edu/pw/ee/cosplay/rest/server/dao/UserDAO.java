package pl.edu.pw.ee.cosplay.rest.server.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUserEntity;

/**
 * DAO - Data Access Object.
 * Spring za nas zaimplementuje interface dostarczając standardowe operacje CRUD na encji.
 * <p/>
 * Poniżej dodałem funkcję, która również "automatycznie" zostanie zaimplementowana na podstawie SQL'a
 * napisanego w języku JPA.
 * <p/>
 * JPA pozwala nam na wyabstrachowanie programu od bazy danych, na której pracujemy (będzie działało dla Oracle, MySQL,
 * M$ i tak dalej...
 */

@Transactional
public interface UserDAO extends CrudRepository<McUserEntity, Long> {

    @Query("SELECT u FROM McUserEntity u WHERE u.username = :username")
    McUserEntity getUserByLogin(@Param("username") String username);

}

