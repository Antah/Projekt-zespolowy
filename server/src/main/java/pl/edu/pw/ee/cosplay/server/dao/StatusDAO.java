package pl.edu.pw.ee.cosplay.server.dao;

import pl.edu.pw.ee.cosplay.server.model.McStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *  DAO - Data Access Object. Właściwie wystarczy rozszerzyć CrudRepository, które samo się zaimplementuje za nas
 *  pozwalając na 4 podstawowe czynności Crud.
 */

@Transactional
public interface StatusDAO extends CrudRepository<McStatus, Long> {
}
