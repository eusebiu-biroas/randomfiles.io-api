package io.randomfiles.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RandomFilesApplication {

    public static void main(String... args) {
        SpringApplication.run(RandomFilesApplication.class);
    }
}
