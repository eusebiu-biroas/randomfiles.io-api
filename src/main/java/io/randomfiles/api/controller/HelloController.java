package io.randomfiles.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
public class HelloController {

    @GetMapping(path = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getHello() {

        return "Hello from randomfiles.io, " + LocalDateTime.now();
    }
}
