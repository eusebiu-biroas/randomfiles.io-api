package io.randomfiles.api.controller;

import io.randomfiles.api.service.xml.XMLService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/rest/v1/xml")
public class XMLController {

    private XMLService xmlService;

    @Inject
    public XMLController(XMLService xmlService) {
        this.xmlService = xmlService;
    }

    @GetMapping
    public ResponseEntity<Resource> getXML() throws ParserConfigurationException, TransformerException {

        ByteArrayOutputStream byteArrayOutputStream = xmlService.generateXML();
        ByteArrayResource txtByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io.xml");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(txtByteArray.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(txtByteArray);
    }
}
