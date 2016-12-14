package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

import static ru.javaops.masterjava.xml.util.Constants.TEST_STRING;

/**
 * Created by apyreev on 13-Dec-16.
 */
public class XsltProcessorTest {

    @Test
    public void transform() throws Exception {
        try (InputStream xslInputStream = Resources.getResource(XsltProcessorTest.class, "/cities.xsl").openStream();
             InputStream xmlInputStream = Resources.getResource(XsltProcessorTest.class, "/payload.xml").openStream()){

            XsltProcessor processor = new XsltProcessor(xslInputStream);
            String resultString = processor.transform(xmlInputStream);
            Assert.assertEquals(TEST_STRING, resultString);
        }
    }
}