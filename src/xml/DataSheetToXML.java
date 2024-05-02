package xml;

import TableBean.Data;
import TableBean.DataSheet;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public abstract class DataSheetToXML {

    private static Document document;

    public static void saveXMLDoc(DataSheet sheet, String filepath){
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        document = builder.newDocument();
        addRootElement("list");
        for (Data data : sheet.getDataTable()) {
            addElement(data);
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filepath.trim()));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }


    private static void addRootElement(String name) {
        Element root = document.createElement(name);
        document.appendChild(root);
    }

    private static void addElement(Data data) {
        Element dataEl = document.createElement("point");
        Attr attr = document.createAttribute("x");
        attr.setValue(String.valueOf(data.getX()));
        dataEl.setAttributeNode(attr);
        attr = document.createAttribute("y");
        attr.setValue(String.valueOf(data.getY()));
        dataEl.setAttributeNode(attr);
        attr = document.createAttribute("date");
        attr.setValue(String.valueOf(data.getDate()));
        dataEl.setAttributeNode(attr);
        document.getDocumentElement().appendChild(dataEl);

    }
}
