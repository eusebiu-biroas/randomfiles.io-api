package io.randomfiles.api.service.xml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xml")
public class XMLServiceConfiguration {
    private int indentAmount;

    public int getIndentAmount() {
        return indentAmount;
    }

    public void setIndentAmount(int indentAmount) {
        this.indentAmount = indentAmount;
    }
}
