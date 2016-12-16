package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/**
 * Created by apyreev on 12-Dec-16.
 */
public class StaxStreamProcessorTest {

    @Test
    public void readCities() throws Exception {
        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource(Constants.XML_FILE).openStream())) {
            StringBuilder resultString = new StringBuilder();
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLEvent.START_ELEMENT) {
                    if("City".equals(reader.getLocalName())) {
                        resultString.append(reader.getElementText()).append("\r\n");
                    }
                }
            }
            Assert.assertEquals(Constants.TEST_CITY_STRING, resultString.toString());
        }
    }

    @Test
    public void readElements() throws Exception {
        Assert.assertEquals(Constants.TEST_CITY_STRING, getElementsFromXML("City"));
        Assert.assertEquals(Constants.TEST_USER_STRING, getElementsFromXML("User"));
    }

    private String getElementsFromXML(String element) throws Exception {
        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource(Constants.XML_FILE).openStream())) {
            StringBuilder result = new StringBuilder();
            String user;
            while ((user = processor.getElementValue(element)) != null) {
                result.append(user).append("\r\n");
            }
            return result.toString();
        }
    }
}