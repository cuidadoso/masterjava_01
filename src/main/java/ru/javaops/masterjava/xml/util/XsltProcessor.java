package ru.javaops.masterjava.xml.util;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by apyreev on 13-Dec-16.
 */
public class XsltProcessor {
    private static TransformerFactory FACTORY = TransformerFactory.newInstance();
    private final Transformer xformer;

    public XsltProcessor(InputStream xslInputStream) throws TransformerConfigurationException {
        this(new BufferedReader(new InputStreamReader(xslInputStream, StandardCharsets.UTF_8)));
    }

    public XsltProcessor(Reader xsltReader) throws TransformerConfigurationException {
            Templates template = FACTORY.newTemplates(new StreamSource(xsltReader));
            xformer = template.newTransformer();
    }

    public String transform(InputStream xmlInputStream) throws TransformerException {
        StringWriter out = new StringWriter();
        transform(xmlInputStream, out);
        return out.getBuffer().toString();
    }

    private void transform(InputStream xmlInputStream, StringWriter result) throws TransformerException {
        transform(new BufferedReader(new InputStreamReader(xmlInputStream, StandardCharsets.UTF_8)), result);
    }

    public void transform(Reader sourceReader, Writer result) throws TransformerException {
        xformer.transform(new StreamSource(sourceReader), new StreamResult(result));
    }

    public static String getXsltHeader(String xslt) {
        return "<?xml-stylesheet type=\"text/xsl\" href=\"" + xslt + "\"?>\n";
    }
}
