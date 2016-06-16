package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.CommentData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.server.entity.McCommentEntity;
import pl.edu.pw.ee.cosplay.rest.server.entity.McPhotoEntity;


@RestController()
@RequestMapping(UrlData.GET_PHOTO_PATH)
public class GetPhotoController extends AutowiredController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getPhoto(@RequestBody byte[] byteInput) {
        GetPhotoInput input = (GetPhotoInput) SerializationUtils.deserialize(byteInput);
        GetPhotoOutput output = new GetPhotoOutput();

        mockOutput(input, output);

        byte[] byteOutput = SerializationUtils.serialize(output);
        return new ResponseEntity<>(byteOutput, HttpStatus.OK);
    }

    private void mockOutput(GetPhotoInput input, GetPhotoOutput output) {

        McPhotoEntity photoEntity = photoDAO.findOne(input.getPhotoId());
        output = (GetPhotoOutput) McPhotoEntity.createPhotoDataFromPhotoEntity(output, photoEntity);
        output.setDescription(photoEntity.getDescription());
        for (McCommentEntity commentEntity : photoEntity.getCommentsByPhotoId()) {
            CommentData commentData = new CommentData();
            commentData.setCommentDate(commentEntity.getCommentDate());
            commentData.setComment(commentEntity.getContent());
            commentData.setUsername(commentEntity.getUserByUsername().getUsername());
            if (commentEntity.getUserByUsername().getBinaryPhotoByAvatarBinaryPhotoId() != null)
                commentData.setAvatarBinaryData(commentEntity.getUserByUsername().getBinaryPhotoByAvatarBinaryPhotoId().getBinaryData());
            output.getComments().add(commentData);
        }
    }

}
