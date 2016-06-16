package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.RegisterInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.RegisterOutput;
import pl.edu.pw.ee.cosplay.rest.server.entity.McUserEntity;
import pl.edu.pw.ee.cosplay.rest.server.security.AES;


@RestController()
@RequestMapping(UrlData.REGISTER_PATH)
public class RegisterController extends AutowiredController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody byte[] byteInput) {
        RegisterInput input = (RegisterInput) SerializationUtils.deserialize(byteInput);

        RegisterOutput output = new RegisterOutput();

        McUserEntity userEntity = userDAO.getUserByLogin(input.getUsername());
        if (userEntity == null) {
            mockOutput(input, output);
            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with such username already exists.", HttpStatus.BAD_REQUEST);
        }
    }

    private void mockOutput(RegisterInput input, RegisterOutput output) {
        McUserEntity userEntity = new McUserEntity();
        userEntity.setUsername(input.getUsername());
        userEntity.setPasswd(AES.encrypt(input.getPassword()));
        userDAO.save(userEntity);
    }


}
