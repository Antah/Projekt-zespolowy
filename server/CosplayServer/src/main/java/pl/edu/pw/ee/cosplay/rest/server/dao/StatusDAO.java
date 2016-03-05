package pl.edu.pw.ee.cosplay.rest.server.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.ee.cosplay.rest.model.entity.McStatus;

/**
 * DAO - Data Access Object.
 * Spring za nas zaimplementuje interface dostarczając standardowe operacje CRUD na encji.
 */

@Transactional
public interface StatusDAO extends CrudRepository<McStatus, Long> {
}

