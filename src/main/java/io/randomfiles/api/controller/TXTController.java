package io.randomfiles.api.controller;

import io.randomfiles.api.service.txt.TXTService;
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
@RequestMapping("/api/rest/v1/txt")
public class TXTController {

    private TXTService txtService;

    @Inject
    public TXTController(TXTService txtService){
        this.txtService= txtService;
    }

    @GetMapping
    public ResponseEntity<Resource> getTXT() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = txtService.generateTXT();
        ByteArrayResource txtByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io.txt");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(txtByteArray.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(txtByteArray);
    }

    @GetMapping("batch/{batchSize}")
    public ResponseEntity<Resource> getTXTBatch(@PathVariable int batchSize) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = txtService.generateTXTBatch(batchSize);
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
