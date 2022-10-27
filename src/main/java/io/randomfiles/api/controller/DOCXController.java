package io.randomfiles.api.controller;

import io.randomfiles.api.service.docx.DOCXService;
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
@RequestMapping("/api/rest/v1/docx")
public class DOCXController {
    private DOCXService docxService;

    @Inject
    public DOCXController(DOCXService docxService) {
        this.docxService = docxService;
    }

    @GetMapping
    public ResponseEntity<Resource> getDOCX() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = docxService.generateDOCX();
        ByteArrayResource xlsByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io.docx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsByteArray.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(xlsByteArray);
    }

    @GetMapping("batch/{batchSize}")
    public ResponseEntity<Resource> getDOCXBatch(@PathVariable int batchSize) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = docxService.generateDOCXBatch(batchSize);
        ByteArrayResource zipByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io-docx.zip");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(zipByteArray.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(zipByteArray);
    }
}
