package pl.edu.pw.ee.cosplay.rest.server.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.ee.cosplay.rest.model.entity.McStatus;

/**
 * DAO - Data Access Object. Właściwie wystarczy rozszerzyć CrudRepository, które samo się zaimplementuje za nas
 * pozwalając na 4 podstawowe czynności Crud.
 */

@Transactional
public interface StatusDAO extends CrudRepository<McStatus, Long> {
}

