package ru.javaops.masterjava.xml.util;

import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import static ru.javaops.masterjava.xml.util.Constants.TEST_STRING;

/**
 * Created by apyreev on 12-Dec-16.
 */
public class StaxStreamProcessorTest {

    @Test
    public void readCities() throws Exception {
        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource(StaxStreamProcessorTest.class, "/payload.xml").openStream())) {
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
            Assert.assertEquals(TEST_STRING, resultString.toString());
        }
    }

    @Test
    public void readCities2() throws Exception {
        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource(StaxStreamProcessorTest.class, "/payload.xml").openStream())) {
            StringBuilder resultString = new StringBuilder();
            String city;
            while ((city = processor.getElementValue("City")) != null) {
                resultString.append(city).append("\r\n");
            }
            Assert.assertEquals(TEST_STRING, resultString.toString());
        }
    }
}