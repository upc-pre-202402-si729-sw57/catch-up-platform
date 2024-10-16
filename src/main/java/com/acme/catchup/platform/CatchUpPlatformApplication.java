package com.acme.catchup.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * CatchUpPlatformApplication
 * @summary
 * CatchUpPlatformApplication is the main class that represents the entry point of the application.
 * It is responsible for starting the Spring Boot application.
 * It also enables JPA auditing.
 * @since 1.0.0
 */
@EnableJpaAuditing
@SpringBootApplication
public class CatchUpPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatchUpPlatformApplication.class, args);
    }

}
