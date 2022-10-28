package io.randomfiles.api.service.docx;

import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DOCXService {

    RandomService randomService;
    GeneralConfiguration config;

    @Inject
    public DOCXService(RandomService randomService, GeneralConfiguration config) {
        this.randomService = randomService;
        this.config = config;
    }

    public ByteArrayOutputStream generateDOCX() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (XWPFDocument doc = new XWPFDocument()) {

            XWPFParagraph p = doc.createParagraph();

            XWPFRun r = p.createRun();
            r.setText(randomService.getRandomString());
            r.setBold(true);
            r = p.createRun();
            r.setText(" - " + this.config.getWaterMarkText());

            CTP ctP = CTP.Factory.newInstance();
            CTText t = ctP.addNewR().addNewT();
            t.setStringValue("footer");
            XWPFParagraph[] pars = new XWPFParagraph[1];
            p = new XWPFParagraph(ctP, doc);
            pars[0] = p;

            XWPFHeaderFooterPolicy hfPolicy = doc.createHeaderFooterPolicy();
            hfPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, pars);

            ctP = CTP.Factory.newInstance();
            t = ctP.addNewR().addNewT();
            t.setStringValue("footer");
            pars[0] = new XWPFParagraph(ctP, doc);
            hfPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, pars);

            doc.write(byteArrayOutputStream);

            return byteArrayOutputStream;
        }
    }

    public ByteArrayOutputStream generateDOCXBatch(int batchSize) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(byteArrayOutputStream);
        for (int i = 0; i < batchSize; i++) {
            ByteArrayOutputStream pdfByteArrayOutputStream = generateDOCX();
            ZipEntry zipEntry = new ZipEntry("randomfiles.io-" + (i + 1) + ".docx");
            zipOut.putNextEntry(zipEntry);
            zipOut.write(pdfByteArrayOutputStream.toByteArray());
            pdfByteArrayOutputStream.close();
        }
        zipOut.close();
        return byteArrayOutputStream;
    }
}
