package M9QNOF_1022;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxM9QNOF{
    public static void main(String[] args) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
        	SAXParser saxParser = factory.newSAXParser();
        	DocumentHandler myHandler = new DocumentHandler();
        	saxParser.parse("M9QNOF_1022\\szemelyek.xml", myHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class DocumentHandler extends DefaultHandler {
    private int tab = 0;

	@Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        tabulate(tab);
        System.out.print(qName);
		int attrLength = attr.getLength();
		
        if (attrLength > 0) {
            System.out.print(", {");
            for (int i = 0; i < attrLength; i++) {
                System.out.print(attr.getQName(0) + ":" + attr.getValue(0));
                if (i != attrLength - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("}");
        }
		System.out.print(" start");
		
        if (attrLength == 0 && tab != 0) {
            System.out.println();
        }
        tab++;
    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tabulate(--tab);
        System.out.print(qName + " end\r");
	}
	
	@Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tabulate(tab);
        System.out.println(new String(ch, start, length).trim());
	}
	
	private void tabulate(int tabulation) {
        for (int i = 0; i < tabulation; i++) {
            System.out.print("    ");
        }
    }
}
