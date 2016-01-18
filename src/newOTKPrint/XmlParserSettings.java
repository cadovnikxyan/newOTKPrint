package newOTKPrint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;



public class XmlParserSettings {
	
	static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();   
    DocumentBuilder db = dbf.newDocumentBuilder(); 
    Document doc=null;
    String xmlPath=null;
	public XmlParserSettings(final String xmlpath) 
			throws ParserConfigurationException, SAXException, IOException {
		
			doc = db.parse(new File(xmlpath)); 
			this.xmlPath=xmlpath;

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
	
	public void insertElement(String tag, String text) throws FileNotFoundException, TransformerFactoryConfigurationError, TransformerException {
		Node node=doc.getElementsByTagName(tag).item(0);
//		NamedNodeMap attr= node.getAttributes();
//		Node nodeAttr = attr.getNamedItem(tag);
//		nodeAttr.setTextContent("123");
//		
		
		Text a=doc.createTextNode(text);
	
		Element p=doc.createElement(tag);
		p.appendChild(a);
		
		node.getParentNode().insertBefore(p, node);
		saveXMLfile(doc);
//		
	}
	
		private void saveXMLfile(Document doc) throws TransformerFactoryConfigurationError, TransformerException, FileNotFoundException{
			
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");			
			StreamResult result=new StreamResult(new File(xmlPath));
			DOMSource source= new DOMSource(doc);			
			transformer.transform(source, result);

			
		}
}
