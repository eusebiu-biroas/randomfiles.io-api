package io.randomfiles.api.service.xml;


import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.XMLConstants;
import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class XMLService {

    private RandomService randomService;
    private XMLServiceConfiguration xmlServiceConfiguration;
    private GeneralConfiguration generalConfiguration;


    @Inject
    public XMLService(RandomService randomService, XMLServiceConfiguration xmlServiceConfiguration
            , GeneralConfiguration generalConfiguration) {
        this.randomService = randomService;
        this.xmlServiceConfiguration = xmlServiceConfiguration;
        this.generalConfiguration = generalConfiguration;
    }

    public ByteArrayOutputStream generateXML() throws ParserConfigurationException, TransformerException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document xmlDocument = documentBuilder.newDocument();
        Element rootElement = xmlDocument.createElement("randomXML");

        Element randomElement = xmlDocument.createElement("randomElement");
        randomElement.setAttribute("createdAt", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        Element randomString = xmlDocument.createElement("randomString");
        randomString.appendChild(xmlDocument.createTextNode(randomService.getRandomString()));
        randomElement.appendChild(randomString);

        Element message = xmlDocument.createElement("message");
        message.appendChild(xmlDocument.createTextNode(generalConfiguration.getWaterMarkText()));
        randomElement.appendChild(message);

        rootElement.appendChild(randomElement);
        xmlDocument.appendChild(rootElement);

        transformToXMLString(byteArrayOutputStream, xmlDocument);
        return byteArrayOutputStream;
    }

    private void transformToXMLString(ByteArrayOutputStream byteArrayOutputStream, Document xmlDocument) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount"
                , Integer.toString(xmlServiceConfiguration.getIndentAmount()));
        DOMSource domSource = new DOMSource(xmlDocument);
        StreamResult streamResult = new StreamResult(byteArrayOutputStream);
        transformer.transform(domSource, streamResult);
    }
}
