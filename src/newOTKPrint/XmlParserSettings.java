package newOTKPrint;

import java.io.File;
import java.io.IOException;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;



public class XmlParserSettings {

	static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();   
    DocumentBuilder db = dbf.newDocumentBuilder(); 
    Document doc=null;
    
	public XmlParserSettings(final String xmlpath) throws ParserConfigurationException, SAXException, IOException {
		
			doc = db.parse(new File(xmlpath)); 

	}
	
	public String[] getStringTypes(String tag){
		NodeList _list = doc.getElementsByTagName(tag);
		String[] args = new String[_list.getLength()];
		
		   
		   for(int i=0;i<_list.getLength();i++){
			   Element value= (Element)_list.item(i);
			  args[i]= value.getTextContent();
		   }
		   return args;
	}
	
	public int getNumberValue(String tag)throws XMLParseException{
		Element value = (Element)doc.getElementsByTagName(tag).item(0);
		return Integer.parseInt(value.getTextContent());
	}
	public int getTalonNumberValue(){
		 Element value = (Element)doc.getElementsByTagName("talon").item(0);
		return Integer.parseInt(value.getTextContent());
	}
	public int getWorkNumberValue(){
		 Element value = (Element)doc.getElementsByTagName("work").item(0);
		return Integer.parseInt(value.getTextContent());
	}
	
}
