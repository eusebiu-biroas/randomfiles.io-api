package io.randomfiles.api.service.txt;

import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class TXTService {

    RandomService randomService;
    GeneralConfiguration config;

    @Inject
    public TXTService(RandomService randomService, GeneralConfiguration config) {
        Assert.notNull(randomService, "RandomService may not be null");
        Assert.notNull(randomService, "GeneralConfiguration may not be null");
        this.randomService = randomService;
        this.config = config;
    }

    public ByteArrayOutputStream generateTXT() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String content = randomService.getRandomString()
                + System.lineSeparator()
                + System.lineSeparator()
                + config.getWaterMarkText();

        byteArrayOutputStream.write(content.getBytes());

        return byteArrayOutputStream;
    }
}
