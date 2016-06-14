package pl.edu.pw.ee.cosplay.rest.model.constants;

/**
 * Created by Michał on 2016-02-02.
 * <p/>
 * Stałe z informacjami o błędach.
 */
public class ErrorMessage {
    public static final String WRONG_PASSWORD = "Password doesn't match for login.";
    public static final String WRONG_LOGIN = "There isn't any user with typed login.";
    public static final String NOT_LOGGED = "You're not logged to application";
    public static final String COMMENT_TOO_LONG = "Your comment is too long.";
    public static final String USER_ALREADY_VOTED = "You have already voted on this photo.";
}