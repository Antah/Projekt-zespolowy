package pl.edu.pw.ee.cosplay.rest.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import pl.edu.pw.ee.cosplay.rest.model.entity.*;

/**
 * Klasą służąca do uruchomienia serwera.
 * <p/>
 * W normalnych aplikacjach korzystających z Springa lub Javy Enterprise potrzebny jest dodatkowy
 * serwer aplikacji, którego zarządzanie jest dość kłopotliwe.
 * <p/>
 * <p/>
 * Nasza aplikacjia korzysta z SpringBoot, który dostarcza 'samokonfigurujący' się serwer aplikcaji
 * postawiony na Tomcacie, polega to na tym, że klasy z odpowiednią adnotacją np @RestController są
 * automatycznie dołączane do jego konfiguracji przy starcie.
 * <p/>
 * Konfiguracja:
 * 1) application.properties - ustawienia połączenia z bazą danych
 * 2) CORSFilter - ustawienia pewnych filtrów połączenia
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {
        McUserEntity.class, McCharacteerEntity.class, McPhotoEntity.class, McBinaryPhotoEntity.class,
        McFranchiseEntity.class, McCommentEntity.class, McObservationEntity.class, McRatingEntity.class
})
public class Server {
    public static void main(final String[] args) {
        SpringApplication.run(Server.class, args);
    }
}