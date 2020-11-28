package hu.domparse.mq9nof;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
public class DOMReadM9QNOF {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        //L�trehoz�s
		File xmlFile = new File("src/hu/domparse/mq9nof/XMLM9QNOF.xml");
		//Builder + konvert�l�s
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        //Normaliz�l�s
        doc.getDocumentElement().normalize();
        //Gy�k�relem ki�r�sa
        System.out.println("Gy�k�relem: " + doc.getDocumentElement().getNodeName());
        //versenyzo elemek nList-be illeszt�se
        NodeList nList = doc.getElementsByTagName("versenyzo");
        System.out.println("--------------------------------------------------------");
        //Ki�r�s ciklusa; nList/nNode-on v�gighaladva az elk�sz�tett algoritmusokkal ki�rja a k�rt adatokat
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                String Vid = elem.getAttribute("Vid");
                //Adatok �tad�sa a node-okba
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();
                Node node2 = elem.getElementsByTagName("nemzetiseg").item(0);
                String nemz = node2.getTextContent();
                Node node3 = elem.getElementsByTagName("nem").item(0);
                String nem = node3.getTextContent();
                Node node4 = elem.getElementsByTagName("kategoria").item(0);
                String kat = node4.getTextContent();
                Node node5 = elem.getElementsByTagName("lakcim").item(0);
                String lak = node5.getTextContent();
                //Ki�r�s
                System.out.println("Versenyz� ID: " + Vid);
                System.out.println("Neve: " + nev);
                System.out.println("Nemzetis�g: " + nemz);
                System.out.println("Neme: " + nem);
                System.out.println("Kateg�ria: " + kat);
                System.out.println("Lakhelye " + lak);
                //Met�dus h�v�sok
                listEdzo(doc, Vid);
                System.out.println(nev + " szponzorai:\n" );
                listSzponzor(doc, Vid);
                System.out.println(nev + " Meccsei:\n" );
                listMeccsek(doc, Vid);
                System.out.println("\n");
            }
        }
	}
        //Edz�k adatainak ki�r�sa az el�z� m�dszer alapj�n
        public static void listEdzo (Document doc, String Vid) {
        	NodeList nList = doc.getElementsByTagName("edzo");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    if (elem.getAttribute("Vid").toString().equals(Vid)) {
                        Node node1 = elem.getElementsByTagName("nemzetiseg").item(0);
                        String nemzetiseg = node1.getTextContent();
                        Node node2 = elem.getElementsByTagName("nev").item(0);
                        String nev = node2.getTextContent();
                        System.out.println("Edz� nemzetis�ge: " + nemzetiseg);
                        System.out.println("Edz� neve: " + nev +"\n");
                    }
                }
            }
        }
        //Szponzorok adatainak ki�r�sa az el�z� m�dszer alapj�n
        public static void listSzponzor (Document doc, String Vid) {
        	NodeList nList = doc.getElementsByTagName("szponzor");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    if (elem.getAttribute("Vid").toString().equals(Vid)) {
                        String Sid = elem.getAttribute("Sid");
                        Node node1 = elem.getElementsByTagName("szekhely").item(0);
                        String szekhely = node1.getTextContent();
                        Node node2 = elem.getElementsByTagName("tamogatas").item(0);
                        String tamogatas = node2.getTextContent();
                        System.out.println("Szponzor neve: " + Sid);
                        System.out.println("Sz�khelye: " + szekhely);
                        System.out.println("T�mogat�s �sszege: " + tamogatas + "\n");
                    }
                }
            }
        }
        //Meccsek adatainak ki�r�sa az el�z� m�dszer alapj�n
        public static void listMeccsek (Document doc, String Vid) {
        	NodeList nList = doc.getElementsByTagName("meccs");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    if (elem.getAttribute("Vid").toString().equals(Vid)) {
                        String Mid = elem.getAttribute("Mid");
                        Node node1 = elem.getElementsByTagName("tipus").item(0);
                        String tipus = node1.getTextContent();
                        Node node2 = elem.getElementsByTagName("fajta").item(0);
                        String fajta = node2.getTextContent();
                        System.out.println("Meccs ID: " + Mid);
                        System.out.println("Meccs t�pusa: " + tipus);
                        System.out.println("Meccs fajt�ja: " + fajta + "\n");
                    }
                }
            }
        }     
}
