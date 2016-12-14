package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import java.io.InputStream;
import java.util.stream.IntStream;

import static ru.javaops.masterjava.xml.util.Constants.TEST_STRING;

/**
 * Created by apyreev on 13-Dec-16.
 */
public class XPathProcessorTest {

    @Test
    public void getCities() throws Exception {
        try (InputStream is =
                     Resources.getResource(StaxStreamProcessorTest.class, "/payload.xml").openStream()) {
            XPathProcessor processor = new XPathProcessor(is);
            XPathExpression expression = XPathProcessor.getExpression("/*[name()='Payload']/*[name()='Cities']/*[name()='City']/text()");
            NodeList nodes = processor.evaluete(expression, XPathConstants.NODESET);
            StringBuilder resultString = new StringBuilder();
            IntStream.range(0, nodes.getLength()).forEach(
                    i -> resultString.append(nodes.item(i).getNodeValue()).append("\r\n"));
            Assert.assertEquals(TEST_STRING, resultString.toString());
        }
    }
}