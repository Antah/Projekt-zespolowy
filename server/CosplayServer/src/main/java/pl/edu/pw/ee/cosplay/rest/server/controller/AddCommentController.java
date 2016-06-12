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
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment.AddCommentInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment.AddCommentOutput;
import pl.edu.pw.ee.cosplay.rest.model.entity.McCommentEntity;
import pl.edu.pw.ee.cosplay.rest.server.dao.CommentDAO;

import java.sql.Date;

@RestController()
@RequestMapping(UrlData.ADD_COMMENT_CONTROLLER)
public class AddCommentController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addComment(@RequestBody byte[] byteInput) {
        AddCommentInput input = (AddCommentInput) SerializationUtils.deserialize(byteInput);
        if (true) {

            if (input.getComment().length() > 256) {
                return commentTooLong();
            }

            AddCommentOutput output = new AddCommentOutput();

            //TODO: Implementacja
            mockOutput(input, output);


            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    private ResponseEntity<?> commentTooLong() {
        return new ResponseEntity<>(ErrorMessage.COMMENT_TOO_LONG, HttpStatus.BAD_REQUEST);
    }


    @Autowired
    private CommentDAO commentDAO;

    private void mockOutput(AddCommentInput input, AddCommentOutput output) {
        McCommentEntity commentEntity = new McCommentEntity();
        commentEntity.setContent(input.getComment());
        commentEntity.setUsername(input.getAuthenticationData().getUsername());
        commentEntity.setCommentDate(new Date(System.currentTimeMillis()));
        commentEntity.setPhotoId(input.getPhotoId());
        commentDAO.save(commentEntity);
    }

}
