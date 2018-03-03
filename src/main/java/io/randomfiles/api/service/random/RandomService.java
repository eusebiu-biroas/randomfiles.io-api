package io.randomfiles.api.service.random;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomService {

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }
}
