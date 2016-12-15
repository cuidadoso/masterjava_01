package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import java.io.InputStream;
import java.util.stream.IntStream;

/**
 * Created by apyreev on 13-Dec-16.
 */
public class XPathProcessorTest {

    @Test
    public void getElements() throws Exception {
        Assert.assertEquals(Constants.TEST_CITY_STRING, getElementsFromXML(Constants.EXP_CITY_STRING));
        Assert.assertEquals(Constants.TEST_USER_STRING, getElementsFromXML(Constants.EXP_USER_STRING));
    }

    private String getElementsFromXML(String exp) throws Exception {
        try (InputStream is =
                     Resources.getResource(StaxStreamProcessorTest.class, Constants.XML_FILE).openStream()) {
            XPathProcessor processor = new XPathProcessor(is);
            XPathExpression expression = XPathProcessor.getExpression(exp);
            NodeList nodes = processor.evaluete(expression, XPathConstants.NODESET);
            StringBuilder result = new StringBuilder();
            IntStream.range(0, nodes.getLength()).forEach(
                    i -> result.append(nodes.item(i).getNodeValue()).append("\r\n"));
            return result.toString();
        }
    }
}