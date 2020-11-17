package domM9QNOF;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DomM9QNOF {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        File xmlFile = new File("M9QNOF_1105/szemelyek.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
       
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("szemely");

        for (int i = 0; i < nList.getLength(); i++) {
            
        	Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("kor").item(0);
                String kor = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("nev").item(0);
                String varos = node3.getTextContent();

                System.out.println("id: " + uid);
                System.out.println("\tnév: " + nev);
                System.out.println("\tkor: " + kor);
                System.out.println("\tváros: " + varos);

            }
        }
    }
}
