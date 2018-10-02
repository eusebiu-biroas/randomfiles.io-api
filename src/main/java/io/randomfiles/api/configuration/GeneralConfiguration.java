package io.randomfiles.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class GeneralConfiguration {

    private String waterMarkText;

    public String getWaterMarkText() {
        return waterMarkText;
    }

    public void setWaterMarkText(String waterMarkText) {
        this.waterMarkText = waterMarkText;
    }

}
