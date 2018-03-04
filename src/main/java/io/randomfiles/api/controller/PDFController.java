package io.randomfiles.api.controller;

import com.itextpdf.text.DocumentException;
import io.randomfiles.api.service.pdf.PDFService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/rest/v1/pdf")
public class PDFController {

    private PDFService pdfService;

    @Inject
    public PDFController(PDFService pdfService) {
        Assert.notNull(pdfService, "pdfService may not be null");
        this.pdfService = pdfService;
    }


    @GetMapping()
    public ResponseEntity<Resource> getPDF() throws DocumentException {

        ByteArrayOutputStream byteArrayOutputStream = pdfService.generatePDF();
        ByteArrayResource pdfByteArray = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=randomfiles.io.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfByteArray.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfByteArray);
    }
}
