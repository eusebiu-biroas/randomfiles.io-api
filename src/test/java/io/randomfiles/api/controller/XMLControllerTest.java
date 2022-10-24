package io.randomfiles.api.controller;

import io.randomfiles.api.RandomFilesApplication;
import io.randomfiles.api.service.xml.XMLService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomFilesApplication.class)
@AutoConfigureMockMvc
public class XMLControllerTest {
    @Inject
    private MockMvc mockMvc;
    @MockBean
    private XMLService xmlService;

    @Before
    public void setUp() throws Exception {

        ByteArrayOutputStream byteArrayOutputStreamStream = new ByteArrayOutputStream();
        byteArrayOutputStreamStream.write(1);

        Mockito.when(xmlService.generateXML()).thenReturn(byteArrayOutputStreamStream);
        Mockito.when(xmlService.generateXMLBatch(Mockito.anyInt())).thenReturn(byteArrayOutputStreamStream);
    }

    @Test
    public void getJSONTest() throws Exception {
        String url = "/api/rest/v1/xml";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assert (mvcResult.getResponse().getContentLength() > 0);
        Assert.assertThat(mvcResult.getResponse().getContentType()
                , CoreMatchers.equalTo(MediaType.APPLICATION_OCTET_STREAM_VALUE));

    }

    @Test
    public void getXMLBatchTest() throws Exception {
        String url = "/api/rest/v1/xml/batch/3";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assert (mvcResult.getResponse().getContentLength() > 0);
        Assert.assertThat(mvcResult.getResponse().getContentType()
                , CoreMatchers.equalTo(MediaType.APPLICATION_OCTET_STREAM_VALUE));
    }
}
