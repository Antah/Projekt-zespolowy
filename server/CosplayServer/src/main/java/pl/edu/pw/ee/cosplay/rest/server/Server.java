package pl.edu.pw.ee.cosplay.rest.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Klasą służąca do uruchomienia serwera.
 */
@SpringBootApplication
public class Server {
    public static void main(final String[] args) {
        SpringApplication.run(Server.class, args);
    }
}