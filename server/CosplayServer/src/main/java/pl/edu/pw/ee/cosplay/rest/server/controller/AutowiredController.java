package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.server.dao.*;

/**
 * Created by Micha³ on 2016-06-12.
 */
@RestController
public class AutowiredController {

    @Autowired
    BinaryPhotoDAO binaryPhotoDAO;

    @Autowired
    CharacteerDAO characteerDAO;

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    FranchiseDAO franchiseDAO;

    @Autowired
    ObservationDAO observationDAO;

    @Autowired
    PhotoDAO photoDAO;

    @Autowired
    RatingDAO ratingDAO;

    @Autowired
    UserDAO userDAO;

}
