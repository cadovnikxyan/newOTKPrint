package newOTKPrint;


import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.junit.Test;
import org.xml.sax.SAXException;

public class XmlParserSettingsTest {


	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		XmlParserSettings xml= new XmlParserSettings("settings.xml");
		xml.insertElement("setup", "qwe");
//		String[] ss=xml.getStringTypes("setup");
//		for(String s: ss){
//			System.out.println(s);
//		}
		//fail("Not yet implemented");
	}

}
