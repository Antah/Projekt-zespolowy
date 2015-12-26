package pl.edu.pw.ee.cosplay.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Tu odpalamy sobie serwer (automatycznie wyszukuje sobie controllery)
 */
@SpringBootApplication
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}