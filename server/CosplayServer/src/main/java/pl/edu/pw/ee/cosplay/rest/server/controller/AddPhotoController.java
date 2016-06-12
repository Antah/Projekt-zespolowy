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
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.server.entity.McBinaryPhotoEntity;
import pl.edu.pw.ee.cosplay.rest.server.entity.McCharacteerEntity;
import pl.edu.pw.ee.cosplay.rest.server.entity.McFranchiseEntity;
import pl.edu.pw.ee.cosplay.rest.server.entity.McPhotoEntity;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

import java.sql.Date;

@RestController()
@RequestMapping(UrlData.ADD_PHOTO_PATH)
public class AddPhotoController extends AutowiredController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody byte[] byteInput) {
        AddPhotoInput input = (AddPhotoInput) SerializationUtils.deserialize(byteInput);
        if (LoggedUsers.isLogged(input.getAuthenticationData())) {

            AddPhotoOutput output = new AddPhotoOutput();

            //TODO: Implementacja
            mockOutput(input, output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }


    private void mockOutput(AddPhotoInput input, AddPhotoOutput output) {

        McBinaryPhotoEntity binaryPhotoEntity = new McBinaryPhotoEntity();
        binaryPhotoEntity.setBinaryData(input.getPhotoBinaryData());
        binaryPhotoDAO.save(binaryPhotoEntity);

        McPhotoEntity photoEntity = new McPhotoEntity();
        photoEntity.setDescription(input.getPhotoDescription());
        photoEntity.setUploadDate(new Date(System.currentTimeMillis()));
        photoEntity.setBinaryPhotoByPhotoBinaryPhotoId(binaryPhotoEntity);
        photoEntity.setUserByUsername(userDAO.getUserByLogin(input.getAuthenticationData().getUsername()));
        photoEntity = photoDAO.save(photoEntity);

        for (String franchise : input.getFranchisesList()) {
            McFranchiseEntity franchiseEntity = new McFranchiseEntity();
            franchiseEntity.setFranchiseName(franchise);
            franchiseEntity.setPhotoByPhotoId(photoEntity);
            franchiseDAO.save(franchiseEntity);
        }

        for (String character : input.getCharactersList()) {
            McCharacteerEntity characteerEntity = new McCharacteerEntity();
            characteerEntity.setCharacteerName(character);
            characteerEntity.setPhotoByPhotoId(photoEntity);
            characteerDAO.save(characteerEntity);
        }

        output.setAddedPhotoId(photoEntity.getPhotoId());
    }

}
