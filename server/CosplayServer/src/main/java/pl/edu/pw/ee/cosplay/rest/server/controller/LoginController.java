package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.ErrorMessage;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUser;
import pl.edu.pw.ee.cosplay.rest.server.dao.UserDAO;

/**
 * LoginController
 * <p/>
 * Klasa implementujące RESTowe metody. Ta klasa pozwala tylko na zalogowanie się użytkownikowi.
 * Jeżeli się uda to zwracamy status 200 oraz Obiekt McUser reprezentujący zalogowanego użytkownika.
 * <p/>
 * Jeżeli coś pójdzie nie tak (złe hasło) zwracany jest odpowiedni kod błędu oraz komunikat.
 */

@RestController()
@RequestMapping("/login")
public class LoginController {

    //org.apache.log4j.Logger Bardzo przyjemny logger do wyświetlania informacji
    //zdecydowanie lepsze od System.out.println ;-)
    static Logger logger = Logger.getLogger(LoginController.class);

    //Autowired pozwoli nam automatycznie przyłączyć userDAO do operowania na encjach z bazy danych
    @Autowired
    private UserDAO userDAO;

    //GET z dwoma parametrami, login i hasło.
    //Nie mylcie ResponseEntity z Encjami mapującymi bazy danych - to dwie różne rzeczy.
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> login(@RequestParam(value = "login") final String login,
                                   @RequestParam(value = "password") final String password) {

        //TODO: usunąć informację o haśle z logów
        logger.info(String.format("Login GET request. Params: login: '%s' password: '%s'", login, password));
        final McUser user = this.userDAO.getUserByLogin(login);
        if (user == null) {
            logger.info("User not found");
            return new ResponseEntity<>(ErrorMessage.WRONG_LOGIN, HttpStatus.BAD_REQUEST);
        } else {
            if (user.getPassword().equals(password)) {
                logger.info("User found, password correct");
                user.setPassword(null);

                /*
                 *  Serializujemy znalezionego użytkownika do ciągu bajtów.
                 *  Dzięki temu po stronie klienta możemy zdeserializować bajty z powrotem do obiektu.
                 *  Pomyślcie, jak bardzo to upraszcza sytuację, gdybyście zamiast tego musieli pisać
                 *  specjalny parser dla obiektu posiadającego np. 100 zmiennych.
                 *  Jedynym wymogiem jest aby obiekt, który chcemy przesłać rozwijał klasę Serializable.
                 */
                final byte[] serializedUser = SerializationUtils.serialize(user);

                /*
                 *  Pierwszy argument, to nasze zserializowane dane, które wysyłamy w BODY
                 *  Drugi argument, to status. Spring bardzo ładnie opisuje statusy słowami,
                 *  jednak przy odbiorze to dalej stare dobre 200.
                 */
                return new ResponseEntity<>(serializedUser, HttpStatus.OK);

            } else {
                logger.info("User found, password incorrect");
                return new ResponseEntity<>(ErrorMessage.WRONG_PASSWORD, HttpStatus.UNAUTHORIZED);
            }
        }
    }
}

