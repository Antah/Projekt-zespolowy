package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.ee.cosplay.rest.model.constants.ErrorMessage;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerOutput;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUserEntity;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;
import pl.edu.pw.ee.cosplay.rest.server.dao.UserDAO;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

/**
 * LoginController
 */
@RestController()
@RequestMapping(UrlData.LOGIN_PATH)
public class LoginController {

    static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody byte[] byteInput) {
        LoginControllerInput input = (LoginControllerInput) SerializationUtils.deserialize(byteInput);
        final McUserEntity user = userDAO.getUserByLogin(input.getUserName());
        if (user == null) {
            return userNotFound();
        } else {
            return userFound(user, input);
        }
    }

    private ResponseEntity<?> userNotFound(){
        logger.info("User not found");
        return new ResponseEntity<>(ErrorMessage.WRONG_LOGIN, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> userFound(McUserEntity user, LoginControllerInput input) {
        //TODO: Jak już będzie rejestracja to z jakimś szyfrowaniem
        if (user.getPasswd().equals(input.getPassword())) {
            return userFoundCorrectPassword(user);
        } else {
            return userFoundWrongPassword();
        }
    }

    private ResponseEntity<?> userFoundWrongPassword(){
        logger.info("User found, password incorrect");
        return new ResponseEntity<>(ErrorMessage.WRONG_PASSWORD, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<?> userFoundCorrectPassword(McUserEntity user){
        logger.info("User found, password correct");
        AuthenticationData data = LoggedUsers.generateTokenAndAddToLoggedUsers(user);
        user.setPasswd(null);
        LoginControllerOutput output = new LoginControllerOutput();
        output.setAuthenticationData(data);
        final byte[] byteOutput = SerializationUtils.serialize(output);
        return new ResponseEntity<>(byteOutput, HttpStatus.OK);
    }
}

