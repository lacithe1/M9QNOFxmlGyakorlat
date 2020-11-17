package hu.meiit.xpathparsem9qnof;

import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;

public class XpathQueryM9QNOF {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        File xmlFile = new File("M9QNOF_1112/studentM9QNOF.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(xmlFile);
       
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        String expression = "/class/student[@rollno = '593']";
        
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println("\nCurrent Element : " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("Student roll no : " + eElement.getAttribute("rollno"));
                System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                System.out.println("Marks : " + eElement.getElementsByTagName("marks").item(0).getTextContent());
            }
        }
    }
}
