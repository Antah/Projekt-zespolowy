package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.ErrorMessage;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerOutput;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;
import pl.edu.pw.ee.cosplay.rest.server.entity.McUserEntity;
import pl.edu.pw.ee.cosplay.rest.server.security.AES;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

/**
 * LoginController
 */
@RestController()
@RequestMapping(UrlData.LOGIN_PATH)
public class LoginController extends AutowiredController {

    static Logger logger = Logger.getLogger(LoginController.class);

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

    private ResponseEntity<?> userNotFound() {
        logger.info("User not found");
        return new ResponseEntity<>(ErrorMessage.WRONG_LOGIN, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> userFound(McUserEntity user, LoginControllerInput input) {
        if (AES.isPasswordCorrect(input.getPassword(), user.getPasswd())) {
            return userFoundCorrectPassword(user);
        } else {
            return userFoundWrongPassword();
        }
    }

    private ResponseEntity<?> userFoundWrongPassword() {
        logger.info("User found, password incorrect");
        return new ResponseEntity<>(ErrorMessage.WRONG_PASSWORD, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<?> userFoundCorrectPassword(McUserEntity user) {
        logger.info("User found, password correct");
        AuthenticationData data = LoggedUsers.generateTokenAndAddToLoggedUsers(user);
        user.setPasswd(null);
        LoginControllerOutput output = new LoginControllerOutput();
        output.setAuthenticationData(data);
        final byte[] byteOutput = SerializationUtils.serialize(output);
        return new ResponseEntity<>(byteOutput, HttpStatus.OK);
    }
}

