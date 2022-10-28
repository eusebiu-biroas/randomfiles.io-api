package io.randomfiles.api.service.xlsx;
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
@SpringBootTest(classes = {XLSXService.class, RandomService.class})
@EnableConfigurationProperties(GeneralConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class XLSXServiceTest {
    @Inject
    private XLSXService xlsxService;

    @Test
    public void generateXLSXTest() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = xlsxService.generateXLSX();

        Assert.assertNotNull(byteArrayOutputStream);
        assert (byteArrayOutputStream.size() > 0);
    }
}
