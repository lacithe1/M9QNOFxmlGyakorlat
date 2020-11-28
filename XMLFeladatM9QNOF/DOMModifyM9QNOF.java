package hu.domparse.mq9nof;
import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;
public class DOMModifyM9QNOF {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
		//Az eredeti XML f�jl �s a megv�ltoztatott xml f�jl megad�sa
        File xmlFile = new File("src/hu/domparse/mq9nof/XMLM9QNOF.xml");
        File xmlFileMODIFIED = new File("src/hu/domparse/mq9nof/XMLM9QNOFMODIFIED.xml");
        //Scanner nyit�sa a  beolvas�shoz
        Scanner sc = new Scanner(System.in);
        //Builder + konvert�l�s
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        //Normaliz�l�s
        doc.getDocumentElement().normalize();
        //A versenyzo elemek kiv�laszt�sa.
        NodeList nList = doc.getElementsByTagName("versenyzo");
        //ciklus a v�ltoztat�sokhoz
        for (int i = 0; i < nList.getLength(); i++) { 
            Node nNode = nList.item(i);
            Element elem = (Element) nNode;
            //N�v bek�r�se
            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();
            //�tad�s a node-nak
            System.out.println("A versenyz� jelenlegi neve:" + nev + "\n");
            System.out.println("Add meg a versenyz� �j nev�t: \n");
            //Bek�r�s billenty�zeten
            String modifiedname = sc.next();
            //Be�ll�t�s node-on kereszt�l
            node1.setTextContent(modifiedname);
        }
        //Scanner z�r�sa
        sc.close();
        //transformer �s domsource haszn�lat�val v�ltoztatjuk a f�jlt
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        //A v�ltoztat�s a result-ba ker�lt
        StreamResult result = new StreamResult(xmlFileMODIFIED);
        //Be�r�sra ker�l a m�dos�tott f�jlba a m�dos�t�s
        transformer.transform(source, result);
	}
}