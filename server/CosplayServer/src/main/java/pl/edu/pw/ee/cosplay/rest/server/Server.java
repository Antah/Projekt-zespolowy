package pl.edu.pw.ee.cosplay.rest.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import pl.edu.pw.ee.cosplay.rest.model.entity.McStatus;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUser;

/**
 * Tu odpalamy sobie serwer (automatycznie wyszukuje sobie controllery)
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {McStatus.class, McUser.class})
public class Server {

    public static void main(final String[] args) {
        SpringApplication.run(Server.class, args);
    }

}