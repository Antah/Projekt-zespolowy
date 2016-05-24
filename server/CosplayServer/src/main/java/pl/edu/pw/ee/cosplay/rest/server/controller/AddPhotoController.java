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
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.model.entity.*;
import pl.edu.pw.ee.cosplay.rest.server.dao.*;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

import java.sql.Date;

@RestController()
@RequestMapping(UrlData.ADD_PHOTO_PATH)
public class AddPhotoController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody byte[] byteInput) {
        AddPhotoInput input = (AddPhotoInput) SerializationUtils.deserialize(byteInput);
        if(LoggedUsers.isLogged(input.getAuthenticationData())){

            AddPhotoOutput output = new AddPhotoOutput();

            //TODO: Implementacja
            mockOutput(input, output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    @Autowired
    BinaryPhotoDAO binaryPhotoDAO;

    @Autowired
    PhotoDAO photoDAO;

    @Autowired
    FranchiseDAO franchiseDAO;

    @Autowired
    CharacteerDAO characteerDAO;

    @Autowired
    RatingDAO ratingDAO;

    private void mockOutput(AddPhotoInput input, AddPhotoOutput output) {

        McBinaryPhotoEntity binaryPhotoEntity = new McBinaryPhotoEntity();
        binaryPhotoEntity.setBinaryData(input.getPhotoBinaryData());
        binaryPhotoDAO.save(binaryPhotoEntity);

        McPhotoEntity photoEntity = new McPhotoEntity();
        photoEntity.setDescription(input.getPhotoDescription());
        photoEntity.setUploadDate(new Date(System.currentTimeMillis()));
        photoEntity.setPhotoBinaryPhotoId(binaryPhotoEntity.getBinaryPhotoId());
        photoEntity.setUsername(input.getAuthenticationData().getUsername());
        photoDAO.save(photoEntity);

        for(String franchise : input.getFranchisesList()){
            McFranchiseEntity franchiseEntity = new McFranchiseEntity();
            franchiseEntity.setFranchiseName(franchise);
            franchiseEntity.setPhotoId(photoEntity.getPhotoId());
            franchiseDAO.save(franchiseEntity);
        }

        for(String character : input.getCharactersList()){
            McCharacteerEntity characteerEntity = new McCharacteerEntity();
            characteerEntity.setCharacteerName(character);
            characteerEntity.setPhotoId(photoEntity.getPhotoId());
            characteerDAO.save(characteerEntity);
        }

        output.setAddedPhotoId(photoEntity.getPhotoId());
    }

}
