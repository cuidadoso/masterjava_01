package ru.javaops.masterjava.xml.util;

/**
 * Created by apyreev on 14-Dec-16.
 */
public class Constants {
    public static final String XML_FILE = "payload.xml";
    public static final String XSD_FILE = "payload.xsd";
    public static final String CITY_XSL_FILE = "cities.xsl";
    public static final String USER_XSL_FILE = "users.xsl";
    public static final String TEST_CITY_STRING = "Санкт-Петербург\r\nКиев\r\nМинск\r\n";
    public static final String TEST_USER_STRING = "Full Name\r\nAdmin\r\nDeleted\r\n";
    public static final String EXP_CITY_STRING = "/*[name()='Payload']/*[name()='Cities']/*[name()='City']/text()";
    public static final String EXP_USER_STRING = "/*[name()='Payload']/*[name()='Users']/*[name()='User']/text()";
}
