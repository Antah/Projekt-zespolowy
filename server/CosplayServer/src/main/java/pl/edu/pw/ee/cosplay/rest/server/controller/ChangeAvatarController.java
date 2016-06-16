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
import pl.edu.pw.ee.cosplay.rest.model.controller.avatar.ChangeAvatarInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.avatar.ChangeAvatarOutput;
import pl.edu.pw.ee.cosplay.rest.server.entity.McBinaryPhotoEntity;
import pl.edu.pw.ee.cosplay.rest.server.entity.McUserEntity;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;


@RestController()
@RequestMapping(UrlData.CHANGE_AVATAR_PATH)
public class ChangeAvatarController extends AutowiredController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> changeAvatar(@RequestBody byte[] byteInput) {
        ChangeAvatarInput input = (ChangeAvatarInput) SerializationUtils.deserialize(byteInput);
        if (LoggedUsers.isLogged(input.getAuthenticationData())) {

            ChangeAvatarOutput output = new ChangeAvatarOutput();

            mockOutput(input, output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    private void mockOutput(ChangeAvatarInput input, ChangeAvatarOutput output) {
        McUserEntity userEntity = userDAO.getUserByLogin(input.getAuthenticationData().getUsername());
        if (userEntity.getBinaryPhotoByAvatarBinaryPhotoId() == null) {
            McBinaryPhotoEntity avatarBinaryPhoto = new McBinaryPhotoEntity();
            avatarBinaryPhoto.setBinaryData(input.getAvatarBinaryData());
            avatarBinaryPhoto = binaryPhotoDAO.save(avatarBinaryPhoto);
            userEntity.setBinaryPhotoByAvatarBinaryPhotoId(avatarBinaryPhoto);
            userDAO.save(userEntity);
        } else {
            McBinaryPhotoEntity avatarBinaryPhoto = userEntity.getBinaryPhotoByAvatarBinaryPhotoId();
            avatarBinaryPhoto.setBinaryData(input.getAvatarBinaryData());
            binaryPhotoDAO.save(avatarBinaryPhoto);
        }
    }

}
