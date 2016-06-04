package pl.edu.pw.ee.cosplay.rest.model.constants;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class UrlData {

    private static String SERVER_IP = "http://127.0.0.1";

    private static Integer PORT = 8080;

    public static final String LOGIN_PATH = "/login";

    public static final String ADD_VOTE_PATH = "/addVote";

    public static final String ADD_PHOTO_PATH = "/addPhoto";

    public static final String GET_PHOTO_PATH = "/getPhoto";

    public static final String GET_PHOTOS_LIST_PATH = "/getPhotosList";

    public static String getServerIp() {
        return SERVER_IP;
    }

    public static void setServerIp(String serverIp) {
        SERVER_IP = serverIp;
    }

    public static Integer getPORT() {
        return PORT;
    }

    public static void setPORT(Integer PORT) {
        UrlData.PORT = PORT;
    }
}
