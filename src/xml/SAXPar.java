package xml;

import TableBean.Data;
import TableBean.DataSheet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


public class SAXPar {
    private static final DataSheet sheet = new DataSheet();

    public static DataSheet XMLReadData(String filename){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
        Handler handler = new Handler();
        try {
            parser.parse(new File(filename), handler);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return sheet;
    }
    public static class Handler extends DefaultHandler {


        private double x;
        private double y;
        private String date;


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(qName.equals("point")){
                x = Double.parseDouble(attributes.getValue("x"));
                y = Double.parseDouble(attributes.getValue("y"));
                date = attributes.getValue("date");
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("point")) {
                sheet.addDataItem(new Data(x, y, date));
            }
        }

    }
}
