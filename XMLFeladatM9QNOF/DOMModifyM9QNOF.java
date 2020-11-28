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
		//Az eredeti XML fájl és a megváltoztatott xml fájl megadása
        File xmlFile = new File("src/hu/domparse/mq9nof/XMLM9QNOF.xml");
        File xmlFileMODIFIED = new File("src/hu/domparse/mq9nof/XMLM9QNOFMODIFIED.xml");
        //Scanner nyitása a  beolvasáshoz
        Scanner sc = new Scanner(System.in);
        //Builder + konvertálás
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        //Normalizálás
        doc.getDocumentElement().normalize();
        //A versenyzo elemek kiválasztása.
        NodeList nList = doc.getElementsByTagName("versenyzo");
        //ciklus a változtatásokhoz
        for (int i = 0; i < nList.getLength(); i++) { 
            Node nNode = nList.item(i);
            Element elem = (Element) nNode;
            //Név bekérése
            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();
            //Átadás a node-nak
            System.out.println("A versenyzõ jelenlegi neve:" + nev + "\n");
            System.out.println("Add meg a versenyzõ új nevét: \n");
            //Bekérés billentyûzeten
            String modifiedname = sc.next();
            //Beállítás node-on keresztül
            node1.setTextContent(modifiedname);
        }
        //Scanner zárása
        sc.close();
        //transformer és domsource használatával változtatjuk a fájlt
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        //A változtatás a result-ba került
        StreamResult result = new StreamResult(xmlFileMODIFIED);
        //Beírásra kerül a módosított fájlba a módosítás
        transformer.transform(source, result);
	}
}