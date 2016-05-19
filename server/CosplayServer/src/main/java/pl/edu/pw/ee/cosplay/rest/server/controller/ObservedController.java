package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.ErrorMessage;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.observed.ObservedInputData;
import pl.edu.pw.ee.cosplay.rest.model.entity.McCharacteerEntity;
import pl.edu.pw.ee.cosplay.rest.model.entity.McPhotoEntity;
import pl.edu.pw.ee.cosplay.rest.server.dao.CharacteerDAO;
import pl.edu.pw.ee.cosplay.rest.server.dao.PhotoDAO;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

//TODO: To tylko przyk³ad jak korzystaæ z AuthenticationData
@RestController()
@RequestMapping(UrlData.OBSERVED_PATH)
public class ObservedController {

    @Autowired
    CharacteerDAO characteerDAO;

    @Autowired
    PhotoDAO photoDAO;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody byte[] observedInput) {
        ObservedInputData input = (ObservedInputData) SerializationUtils.deserialize(observedInput);
        if(LoggedUsers.isLogged(input.getAuthenticationData())){

            characteerDAO.findOne(1);

            McCharacteerEntity chara = characteerDAO.findOne(1);

            photoDAO.findOne(chara.getPhotoId());




            return new ResponseEntity<>("ZALOGOWANY", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }
    
}
