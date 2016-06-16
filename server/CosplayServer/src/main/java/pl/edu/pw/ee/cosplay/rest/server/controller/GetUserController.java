package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.ErrorMessage;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.user.GetUserInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.user.GetUserOutput;
import pl.edu.pw.ee.cosplay.rest.server.entity.McObservationEntity;
import pl.edu.pw.ee.cosplay.rest.server.entity.McUserEntity;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;


@RestController()
@RequestMapping(UrlData.GET_USER_PATH)
public class GetUserController extends AutowiredController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getUser(@RequestBody byte[] byteInput) {
        GetUserInput input = (GetUserInput) SerializationUtils.deserialize(byteInput);
        if (LoggedUsers.isLogged(input.getAuthenticationData())) {

            GetUserOutput output = new GetUserOutput();

            mockOutput(input, output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    private void mockOutput(GetUserInput input, GetUserOutput output) {
        McUserEntity userEntity = userDAO.getUserByLogin(input.getUsername());
        output.setUsername(userEntity.getUsername());
        for (McObservationEntity observationEntity : userEntity.getObservationsByUsername()) {
            output.getObservedBy().add(observationEntity.getUserByObserver().getUsername());
        }
        for (McObservationEntity observationEntity : userEntity.getObservationsByUsername_0()) {
            output.getObserverOf().add(observationEntity.getUserByObserved().getUsername());
        }
        if (userEntity.getBinaryPhotoByAvatarBinaryPhotoId() != null) {
            output.setAvatarBinaryData(userEntity.getBinaryPhotoByAvatarBinaryPhotoId().getBinaryData());
        }
    }

}
