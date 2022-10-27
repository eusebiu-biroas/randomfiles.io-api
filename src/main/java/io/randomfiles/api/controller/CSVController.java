package io.randomfiles.api.controller;

import io.randomfiles.api.service.csv.CSVService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/rest/v1/csv")
public class CSVController {
    private CSVService csvService;

    @Inject
    public CSVController(CSVService csvService){ this.csvService = csvService; }

    @GetMapping
    public ResponseEntity<Resource> getCSV() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = csvService.generateCSV();
        ByteArrayResource csvByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(csvByteArray.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(csvByteArray);
    }

    @GetMapping("batch/{batchSize}")
    public ResponseEntity<Resource> getCSVBatch(@PathVariable int batchSize) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = csvService.generateCSVBatch(batchSize);
        ByteArrayResource zipByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io.zip");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(zipByteArray.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(zipByteArray);
    }
}
