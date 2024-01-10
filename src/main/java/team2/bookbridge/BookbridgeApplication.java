package team2.bookbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookbridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookbridgeApplication.class, args);
    }

}
