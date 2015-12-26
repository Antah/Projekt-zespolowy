package pl.edu.pw.ee.cosplay.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.ee.cosplay.server.dao.UserDAO;
import pl.edu.pw.ee.cosplay.server.model.McUser;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * To co misie lubią najbardziej, piszemy sobie pod jakim adresem będą dostępne metody Rest: PUT, POST, DELETE, GET
 * w tym wypadku to będzie http://localhost:8080/status i tylko GET, bo nic więciej nie potrzeba dla statusu.
 */

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<byte[]> getUsers() {
        StandardGetController<McUser> userStandardGetController = new StandardGetController<>(userDAO);
        return userStandardGetController.getEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postUser(@RequestBody byte[] bytes) {
        try {
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
            McUser user = (McUser) in.readObject();
            if (user.getUserId() != null) {
                McUser oldUser = userDAO.findOne(user.getUserId());
                if (oldUser == null)
                    userDAO.save(user);
                else {
                    oldUser.setLogin(user.getLogin());
                    oldUser.setPassword(user.getPassword());
                    oldUser.setStatusId(user.getStatusId());
                    userDAO.save(oldUser);
                }
            } else {
                userDAO.save(user);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT); //słaba absługa bledow ;-)
        }
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getUser(@PathVariable Long userId) {
        StandardGetController<McUser> userStandardGetController = new StandardGetController<>(userDAO);
        return userStandardGetController.getEntity(userId);
    }
}