package com.androidnc.hoctap.tintuc;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParse {
    public Document getDocument(String xml){
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xml));
            inputSource.setEncoding("UTF-8");
            document = builder.parse(inputSource);

        }catch (ParserConfigurationException e){
            Log.e("Error: ", e.getMessage(), e);
            return null;
        }catch (SAXException e){
            Log.e("Error: ", e.getMessage(), e);
            return null;
        }catch (IOException e){
            Log.e("Error: ", e.getMessage(), e);
            return null;
        }

        return document;
    }

    public String getValue(Element item, String name) {
        NodeList nodeList = item.getElementsByTagName(name);

        return this.getTextNodeValue(nodeList.item(0));
    }

    public final String getTextNodeValue(Node node) {
        Node child;
        if (node != null) {
            if (node.hasChildNodes()) {
                for (child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }

            }
        }
        return "";
    }
}
