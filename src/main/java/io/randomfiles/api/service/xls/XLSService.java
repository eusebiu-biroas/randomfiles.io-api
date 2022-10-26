package io.randomfiles.api.service.xls;

import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class XLSService {

    RandomService randomService;
    GeneralConfiguration config;

    @Inject
    public XLSService(RandomService randomService, GeneralConfiguration config) {
        this.randomService = randomService;
        this.config = config;
    }

    public ByteArrayOutputStream generateXLS() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        for (int rowCount = 0; rowCount < 3; rowCount++) {
            Row row = sheet.createRow(rowCount);
            Cell cell = row.createCell(1);
            cell.setCellValue(randomService.getRandomString());

            cell = row.createCell(2);
            cell.setCellValue(config.getWaterMarkText());

            cell = row.createCell(3);
            cell.setCellValue(ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        }

        workbook.write(byteArrayOutputStream);
        return byteArrayOutputStream;
    }

}
