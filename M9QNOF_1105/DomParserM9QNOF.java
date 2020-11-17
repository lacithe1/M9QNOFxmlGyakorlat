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

public class DomParserM9QNOF {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        File xmlFile = new File("M9QNOF_1105/student.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        
        NodeList nList = doc.getElementsByTagName("student");
        
        System.out.println("--------------------------------------------------------");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                System.out.println("\nCurrent Element: " + nNode.getNodeName());

                String rollno = elem.getAttribute("rollno");

                Node node1 = elem.getElementsByTagName("firstname").item(0);
                String firstname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("lastname").item(0);
                String lastname = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("nickname").item(0);
                String nickname = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("age").item(0);
                String age = node4.getTextContent();

                System.out.println("Student roll no: " + rollno);
                
                System.out.println("First Name: " + firstname);
                
                System.out.println("Last Name: " + lastname);
                
                System.out.println("Nick Name: " + nickname);
                
                System.out.println("Age: " + age);

            }
        }
    }
}
