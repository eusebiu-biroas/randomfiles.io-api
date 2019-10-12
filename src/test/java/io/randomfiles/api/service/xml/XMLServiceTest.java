package io.randomfiles.api.service.xml;

import io.randomfiles.api.configuration.GeneralConfiguration;
import io.randomfiles.api.service.random.RandomService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {XMLService.class, RandomService.class})
@EnableConfigurationProperties({GeneralConfiguration.class, XMLServiceConfiguration.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class XMLServiceTest {

    @Inject
    private XMLService xmlService;

    @Test
    public void generateXMLTest() throws ParserConfigurationException, TransformerException {
        ByteArrayOutputStream byteArrayOutputStream = xmlService.generateXML();

        Assert.assertNotNull(byteArrayOutputStream);
        assert (byteArrayOutputStream.size() > 0);
    }
}
