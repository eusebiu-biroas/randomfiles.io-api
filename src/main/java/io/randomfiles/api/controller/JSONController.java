package io.randomfiles.api.controller;

import io.randomfiles.api.service.json.JSONService;
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
@RequestMapping("/api/rest/v1/json")
public class JSONController {

    private JSONService jsonService;

    @Inject
    public JSONController(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping
    public ResponseEntity<Resource> getJSON() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = jsonService.generateJSON();
        ByteArrayResource jsonByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io.json");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(jsonByteArray.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(jsonByteArray);
    }

    @GetMapping("batch/{batchSize}")
    public ResponseEntity<Resource> getJSONBatch(@PathVariable int batchSize) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = jsonService.generateJSONBatch(batchSize);
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
