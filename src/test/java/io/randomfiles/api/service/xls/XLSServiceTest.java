package io.randomfiles.api.service.xls;

import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import io.randomfiles.api.service.txt.TXTService;
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

import static io.randomfiles.api.common.TestCommons.countFilesInZip;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {XLSService.class, RandomService.class})
@EnableConfigurationProperties(GeneralConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class XLSServiceTest {
    @Inject
    private XLSService xlsService;

    @Test
    public void generateXLSTest() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = xlsService.generateXLS();

        Assert.assertNotNull(byteArrayOutputStream);
        assert (byteArrayOutputStream.size() > 0);
    }
    @Test
    public void generateXLSBatchTest() throws IOException {
        int batchSize = 3;
        ByteArrayOutputStream byteArrayOutputStream = xlsService.generateXLSBatch(batchSize);

        int fileCount = countFilesInZip(byteArrayOutputStream);
        Assert.assertNotNull(byteArrayOutputStream);
        assert (byteArrayOutputStream.size() > 0);
        assert (fileCount == batchSize);
    }
}
