package io.randomfiles.api.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import io.randomfiles.api.service.xlsx.XLSXService;
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
@RequestMapping("/api/rest/v1/xlsx")
public class XLSXController {
    private XLSXService xlsxService;

    @Inject
    public XLSXController(XLSXService xlsxService){
        this.xlsxService= xlsxService;
    }

    @GetMapping
    public ResponseEntity<Resource> getXLSX() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = xlsxService.generateXLSX();
        ByteArrayResource xlsxByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsxByteArray.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(xlsxByteArray);
    }

    @GetMapping("batch/{batchSize}")
    public ResponseEntity<Resource> getXLSXBatch(@PathVariable int batchSize) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = xlsxService.generateXLSXBatch(batchSize);
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
