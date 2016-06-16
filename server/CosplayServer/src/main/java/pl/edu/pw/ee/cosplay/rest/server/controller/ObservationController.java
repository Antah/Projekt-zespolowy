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
import pl.edu.pw.ee.cosplay.rest.model.controller.observation.ObservationInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.observation.ObservationOutput;
import pl.edu.pw.ee.cosplay.rest.server.entity.McObservationEntity;
import pl.edu.pw.ee.cosplay.rest.server.entity.McUserEntity;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;


@RestController()
@RequestMapping(UrlData.OBSERVATION_PATH)
public class ObservationController extends AutowiredController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> observation(@RequestBody byte[] byteInput) {
        ObservationInput input = (ObservationInput) SerializationUtils.deserialize(byteInput);
        if (LoggedUsers.isLogged(input.getAuthenticationData())) {

            ObservationOutput output = new ObservationOutput();

            //TODO: Implementacja
            mockOutput(input, output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    private void mockOutput(ObservationInput input, ObservationOutput output) {
        McUserEntity loggedUser = userDAO.getUserByLogin(input.getAuthenticationData().getUsername());
        McUserEntity observedUser = userDAO.getUserByLogin(input.getUsername());
        if (input.getUnObserve()) {
            for (McObservationEntity observationEntity : loggedUser.getObservationsByUsername_0()) {
                if (observationEntity.getUserByObserved() == observedUser) {
                    observationDAO.delete(observationEntity);
                }
            }
        } else {
            McObservationEntity observationEntity = new McObservationEntity();
            observationEntity.setUserByObserved(observedUser);
            observationEntity.setUserByObserver(loggedUser);
            observationDAO.save(observationEntity);
        }
    }

}
