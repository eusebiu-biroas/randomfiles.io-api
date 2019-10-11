package io.randomfiles.api.service.json;

import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JSONService.class, RandomService.class})
@EnableConfigurationProperties(GeneralConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class JSONServiceTest {

    @Inject
    private JSONService jsonService;

    @Test
    public void generateJSONTest() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = jsonService.generateJSON();

        Assert.assertNotNull(byteArrayOutputStream);
        assert (byteArrayOutputStream.size() > 0);
    }
}
