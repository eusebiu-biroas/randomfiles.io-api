package io.randomfiles.api.service.txt;

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
@SpringBootTest(classes = {TXTService.class, RandomService.class})
@EnableConfigurationProperties(GeneralConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TXTServiceTest {

    @Inject
    private TXTService txtService;

    @Test
    public void generateTXTTest() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = txtService.generateTXT();

        Assert.assertNotNull(byteArrayOutputStream);
        assert (byteArrayOutputStream.size() > 0);
    }
}
