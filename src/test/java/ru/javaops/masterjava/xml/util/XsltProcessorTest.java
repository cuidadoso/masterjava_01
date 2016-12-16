package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by apyreev on 13-Dec-16.
 */
public class XsltProcessorTest {

    @Test
    public void transform() throws Exception {
            Assert.assertEquals(Constants.TEST_CITY_STRING, getElementsFromXML(Constants.CITY_XSL_FILE));
            Assert.assertEquals(Constants.TEST_USER_STRING, getElementsFromXML(Constants.USER_XSL_FILE));
    }

    private String getElementsFromXML(String xslFile) throws Exception {
        try (InputStream xslInputStream = Resources.getResource(xslFile).openStream();
             InputStream xmlInputStream = Resources.getResource(Constants.XML_FILE).openStream()){
            XsltProcessor processor = new XsltProcessor(xslInputStream);
            return processor.transform(xmlInputStream);
        }
    }
}