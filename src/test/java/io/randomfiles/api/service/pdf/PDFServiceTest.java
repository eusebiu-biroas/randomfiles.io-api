package io.randomfiles.api.service.pdf;

import com.itextpdf.text.DocumentException;
import io.randomfiles.api.service.random.RandomService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PDFService.class, RandomService.class})
@EnableConfigurationProperties(PDFServiceConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PDFServiceTest {

    @Inject
    private PDFService pdfService;

    @Test
    public void generatePDFTest() throws DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = pdfService.generatePDF();

        Assert.assertNotNull(byteArrayOutputStream);
        assert (byteArrayOutputStream.size() > 0);
    }

    @Test
    public void generatePDFBatchTest() throws DocumentException, IOException {
        int batchSize = 3;
        ByteArrayOutputStream byteArrayOutputStream = pdfService.generatePDFBatch(batchSize);

        int fileCount = unzip(byteArrayOutputStream);
        Assert.assertNotNull(byteArrayOutputStream);
        assert (byteArrayOutputStream.size() > 0);
        assert (fileCount == batchSize);
    }

    private int unzip(ByteArrayOutputStream file) throws IOException {
        int count = 0;
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(file.toByteArray()));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            count++;
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return count;
    }
}
