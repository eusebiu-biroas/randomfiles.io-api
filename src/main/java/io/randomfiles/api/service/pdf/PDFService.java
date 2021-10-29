package io.randomfiles.api.service.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import io.randomfiles.api.service.random.RandomService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class PDFService {

    private PDFServiceConfiguration config;
    private RandomService randomService;

    @Inject
    public PDFService(PDFServiceConfiguration pdfServiceConfiguration, RandomService randomService) {
        Assert.notNull(pdfServiceConfiguration, "pdfServiceConfiguration may not be null");
        Assert.notNull(randomService, "randomService may not be null");
        this.config = pdfServiceConfiguration;
        this.randomService = randomService;
    }

    public ByteArrayOutputStream generatePDF() throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        Paragraph paragraph1 = new Paragraph(randomService.getRandomString(), config.getFont());
        document.add(paragraph1);
        document.add(Chunk.NEWLINE);
        Paragraph paragraph2 = new Paragraph(config.getWaterMarkText(), config.getFont());
        paragraph2.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph2);
        document.close();

        return byteArrayOutputStream;
    }

    public ByteArrayOutputStream generatePDFBatch(int batchSize) throws DocumentException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(byteArrayOutputStream);
        for (int i = 0; i < batchSize; i++) {
            ByteArrayOutputStream pdfByteArrayOutputStream = generatePDF();
            ZipEntry zipEntry = new ZipEntry("randomfiles.io-" + (i + 1) + ".pdf");
            zipOut.putNextEntry(zipEntry);
            zipOut.write(pdfByteArrayOutputStream.toByteArray());
            pdfByteArrayOutputStream.close();
        }
        zipOut.close();
        return byteArrayOutputStream;
    }
}
