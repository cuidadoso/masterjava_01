package ru.javaops.masterjava.xml.util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by apyreev on 13-Dec-16.
 */
public class XPathProcessor {
    private static final DocumentBuilderFactory DOCUMENT_FACTORY = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder DOCUMENT_BUILDER;

    private static final XPathFactory XPATH_FACTORY = XPathFactory.newInstance();
    private static final XPath XPATH = XPATH_FACTORY.newXPath();

    static {
        DOCUMENT_FACTORY.setNamespaceAware(true);
        try {
            DOCUMENT_BUILDER = DOCUMENT_FACTORY.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private final Document doc;

    public XPathProcessor(InputStream is) throws IOException, SAXException, ParserConfigurationException {
        doc = DOCUMENT_BUILDER.parse(is);
    }

    public static synchronized XPathExpression getExpression(String exp) throws XPathExpressionException {
        return XPATH.compile(exp);
    }

    public <T> T evaluete(XPathExpression expression, QName type) throws XPathExpressionException {
        return (T) expression.evaluate(doc, type);
    }
}
