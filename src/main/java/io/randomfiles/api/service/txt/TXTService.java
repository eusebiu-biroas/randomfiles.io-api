package io.randomfiles.api.service.txt;

import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public ByteArrayOutputStream generateTXTBatch(int batchSize) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(byteArrayOutputStream);
        for (int i = 0; i < batchSize; i++) {
            ByteArrayOutputStream pdfByteArrayOutputStream = generateTXT();
            ZipEntry zipEntry = new ZipEntry("randomfiles.io-" + (i + 1) + ".txt");
            zipOut.putNextEntry(zipEntry);
            zipOut.write(pdfByteArrayOutputStream.toByteArray());
            pdfByteArrayOutputStream.close();
        }
        zipOut.close();
        return byteArrayOutputStream;
    }
}
