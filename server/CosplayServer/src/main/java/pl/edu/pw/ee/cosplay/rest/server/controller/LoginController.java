package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.ErrorMessage;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUser;
import pl.edu.pw.ee.cosplay.rest.server.dao.UserDAO;

/**
 * Created by Michał on 2016-02-02.
 */

@RestController()
@RequestMapping("/login")
public class LoginController {

    static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> login(@RequestParam(value = "login") final String login,
                                   @RequestParam(value = "password") final String password) {
        //TODO: usunąć informację o haśle
        logger.info(String.format("Login POST request. Params: login: '%s' password: '%s'", login, password));
        final McUser user = this.userDAO.getUserByLogin(login);
        if (user == null) {
            logger.info("User not found");
            return new ResponseEntity<>(ErrorMessage.WRONG_LOGIN, HttpStatus.BAD_REQUEST);
        } else {
            if (user.getPassword().equals(password)) {
                logger.info("User found, password correct");
                user.setPassword(null);
                final byte[] serializedUser = SerializationUtils.serialize(user);
                return new ResponseEntity<>(serializedUser, HttpStatus.OK);
            } else {
                logger.info("User found, password incorrect");
                return new ResponseEntity<>(ErrorMessage.WRONG_PASSWORD, HttpStatus.UNAUTHORIZED);
            }
        }
    }
}

