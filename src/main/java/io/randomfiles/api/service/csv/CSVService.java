package io.randomfiles.api.service.csv;

import com.opencsv.CSVWriter;
import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CSVService {

    private final RandomService randomService;
    private final GeneralConfiguration generalConfiguration;

    @Inject
    public CSVService(RandomService randomService, GeneralConfiguration generalConfiguration) {
        this.randomService = randomService;
        this.generalConfiguration = generalConfiguration;
    }

    public ByteArrayOutputStream generateCSV() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(byteArrayOutputStream);
        CSVWriter writer = new CSVWriter(streamWriter);

        String[] header = { "Random string", "Message", "Created at" };
        writer.writeNext(header);

        String[] data = { randomService.getRandomString(),
                generalConfiguration.getWaterMarkText(),
                ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME) };
        writer.writeNext(data);

        streamWriter.flush();
        return byteArrayOutputStream;
    }

    public ByteArrayOutputStream generateCSVBatch(int batchSize) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(byteArrayOutputStream);
        for (int i = 0; i < batchSize; i++) {
            ByteArrayOutputStream csvByteArrayOutputStream = generateCSV();
            ZipEntry zipEntry = new ZipEntry("randomfiles.io-" + (i + 1) + ".csv");
            zipOut.putNextEntry(zipEntry);
            zipOut.write(csvByteArrayOutputStream.toByteArray());
            csvByteArrayOutputStream.close();
        }
        zipOut.close();
        return byteArrayOutputStream;
    }
}
