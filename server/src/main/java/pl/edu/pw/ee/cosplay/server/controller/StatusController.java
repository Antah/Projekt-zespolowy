package pl.edu.pw.ee.cosplay.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.server.dao.StatusDAO;
import pl.edu.pw.ee.cosplay.server.model.McStatus;

import java.io.IOException;

/**
 * To co misie lubią najbardziej, piszemy sobie pod jakim adresem będą dostępne metody Rest: PUT, POST, DELETE, GET
 * w tym wypadku to będzie http://localhost:8080/status i tylko GET, bo nic więciej nie potrzeba dla statusu.
 */

@RestController()
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusDAO statusDao;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<byte[]> getStatus() throws IOException {
        StandardGetController<McStatus> statusStandardGetController = new StandardGetController<>(statusDao);
        return statusStandardGetController.getEntities();
    }


    @RequestMapping(value = "/{statusId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getStatus(@PathVariable Long statusId) {
        StandardGetController<McStatus> statusStandardGetController = new StandardGetController<>(statusDao);
        return statusStandardGetController.getEntity(statusId);
    }
}