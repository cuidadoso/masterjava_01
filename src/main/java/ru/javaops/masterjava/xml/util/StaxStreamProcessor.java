package ru.javaops.masterjava.xml.util;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by apyreev on 12-Dec-16.
 */
public class StaxStreamProcessor implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLStreamReader reader;

    public StaxStreamProcessor(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    public boolean doUntil(int stopEvent, String value) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent) {
                if(value.equals(getValue(event))) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getAttribute(String name) throws XMLStreamException {
        int count = reader.getAttributeCount();
        for (int i = 0; i < count; i++) {
            if (reader.getAttributeLocalName(i).equals(name)) {
                return reader.getAttributeValue(i);
            }
        }
        return null;
    }

    public String doUntilAny(int stopEvent, String... values) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent) {
                String xmlValue = getValue(event);
                for (String value : values) {
                    if (value.equals(xmlValue)) {
                        return xmlValue;
                    }
                }
            }
        }
        return null;
    }

    public String getValue(int event) {
        return event == XMLEvent.CHARACTERS ? reader.getText() : reader.getLocalName();
    }

    public String getElementValue(String element) throws XMLStreamException {
        return doUntil(XMLEvent.START_ELEMENT, element) ? reader.getElementText() : null;
    }

    @Override
    public void close() throws Exception {
        if(reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                //empty
            }
        }
    }
}
