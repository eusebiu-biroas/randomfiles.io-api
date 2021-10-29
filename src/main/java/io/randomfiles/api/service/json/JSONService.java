package io.randomfiles.api.service.json;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class JSONService {

    private RandomService randomService;
    private GeneralConfiguration generalConfiguration;

    @Inject
    public JSONService(RandomService randomService, GeneralConfiguration generalConfiguration) {
        this.randomService = randomService;
        this.generalConfiguration = generalConfiguration;
    }

    public ByteArrayOutputStream generateJSON() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("randomString", randomService.getRandomString());
        objectNode.put("message", generalConfiguration.getWaterMarkText());
        objectNode.put("createdAt", ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        byteArrayOutputStream.write(objectNode.toString().getBytes());
        return byteArrayOutputStream;
    }

    public ByteArrayOutputStream generateJSONBatch(int batchSize) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(byteArrayOutputStream);
        for (int i = 0; i < batchSize; i++) {
            ByteArrayOutputStream pdfByteArrayOutputStream = generateJSON();
            ZipEntry zipEntry = new ZipEntry("randomfiles.io-" + (i + 1) + ".pdf");
            zipOut.putNextEntry(zipEntry);
            zipOut.write(pdfByteArrayOutputStream.toByteArray());
            pdfByteArrayOutputStream.close();
        }
        zipOut.close();
        return byteArrayOutputStream;
    }
}
