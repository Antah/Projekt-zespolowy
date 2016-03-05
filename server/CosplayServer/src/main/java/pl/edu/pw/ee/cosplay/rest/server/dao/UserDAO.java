package pl.edu.pw.ee.cosplay.rest.server.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUser;

/**
 * DAO - Data Access Object. Właściwie wystarczy rozszerzyć CrudRepository, które samo się zaimplementuje za nas
 * pozwalając na 4 podstawowe czynności Crud.
 */

@Transactional
public interface UserDAO extends CrudRepository<McUser, Long> {
    @Query("SELECT u FROM McUser u WHERE u.login = :login")
    McUser getUserByLogin(@Param("login") String login);
}

