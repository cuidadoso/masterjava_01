package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Test;

import java.io.InputStream;

/**
 * gkislin
 * 23.09.2016
 */
public class XsltProcessorTest {
    @Test
    public void transform() throws Exception {
        try (InputStream xslInputStream = Resources.getResource(JaxbParserTest.class, "/cities.xsl").openStream();
             InputStream xmlInputStream = Resources.getResource(JaxbParserTest.class, "/payload.xml").openStream()) {

            XsltProcessor processor = new XsltProcessor(xslInputStream);
            System.out.println(processor.transform(xmlInputStream));
        }
    }
}
