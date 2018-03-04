package io.randomfiles.api.service.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pdf")
public class PDFServiceConfiguration {

    private String waterMarkText;
    private String fontName;
    private int fontSize;
    private String fontStyle;

    public String getWaterMarkText() {
        return waterMarkText;
    }

    public void setWaterMarkText(String waterMarkText) {
        this.waterMarkText = waterMarkText;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    @Bean
    public Font getFont() {
        return FontFactory.getFont(this.getFontName(), this.getFontSize()
                , Font.getStyleValue(this.getFontStyle()), BaseColor.BLACK);
    }
}
