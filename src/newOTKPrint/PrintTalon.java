package newOTKPrint;

import javax.print.*;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.standard.PrinterName;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class PrintTalon  {

	private String mediaId=null;
    private String printerName = null;
    private AttributeSet aset = new HashAttributeSet();
    private Map<Integer, Media> trayMap = new HashMap<Integer, Media>(10);
    private PrintService[] services=null;
    private MediaTray selectedTray=null;
    private PrintService defaultPrintService =null;
    private ArrayList<String> trayArray=new ArrayList<String>();
    
    private PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
    
    
	public PrintTalon() {

		// get default printer
		defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
		
		// suggest the use of the default printer
//		System.out.println("Write the printer name or press enter for [" 
//				+ defaultPrintService.getName() + "]");
//		
		// if there is no input, use the default printer
		
		printerName = defaultPrintService.getName();
		
	    }

	static public ArrayList<String> getSystemPrintres(){
		ArrayList<String> printerArray=new ArrayList<String>();
		PrintService[] prints=PrintServiceLookup.lookupPrintServices(null, null);
		for(Object o : prints){
			printerArray.add(o.toString().replace("Win32 Printer :", "")
					.replace("\\\\ps2\\" ,""));
		}
		return printerArray;
		
	}
	public void init(){

        // the printer is selected
        
        aset.add(new PrinterName(printerName, null));

        // selection of all print services
        services = PrintServiceLookup.lookupPrintServices(null, aset);
        // we store all the tray in a hashmap
      
        filTrayName();
     
        //System.out.println("Select tray target id : ");
        // mediaId = console.nextLine();
      
	}
	public void createJob(){
		  if(mediaId!=null){
	        	
		        selectTray(mediaId);

		            // we have to add the MediaTray selected as attribute
		            
		            attributes.add(selectedTray);

		            // we create the printer job, it print a specified document with a set of job attributes
		            DocPrintJob job = services[0].createPrintJob();
		         
		            try {
		                System.out.println("Trying to print an  page on : " +
		            selectedTray.toString());
		                // we create a document that implements the printable interface
		                Doc doc = new SimpleDoc(new PrintPage(),
		                		DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);

		                // we print using the selected attributes (paper tray)
		                job.print(doc, attributes);
		                
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
	}
	
	public void setPrinterName(String printer){
		this.printerName=printer;
	}
	public void setMediaId(String id){
		this.mediaId=id;
	}
	
	
	public ArrayList<String> getTrayArray(){
		return trayArray;
	}
	public String getPrintersName(){
		
		return defaultPrintService.getName();
	}
	
	public void filTrayName(){
		
		   // we chose something compatible with the printable interface
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;

        for (PrintService service : services) {
//            System.out.println(service);

            // we retrieve all the supported attributes of type Media
            // we can receive MediaTray, MediaSizeName, ...
            Object o = service.getSupportedAttributeValues(Media.class, flavor, null);
            if (o != null && o.getClass().isArray()) {
                for (Media media : (Media[]) o) {
                    // we collect the MediaTray available
                    if (media instanceof MediaTray) {
//                        System.out.println(media.getValue() + " : "
//                    + media + " - " + media.getClass().getName());
                    	
                    	trayArray.add(new String(media+"").replace("-Feeder", ""));
                        trayMap.put(media.getValue(), media);
                        
                        
                    }
                }
            }
        }

	}
	
	private void selectTray(String t){
		
		   this.selectedTray = (MediaTray) trayMap.get(Integer.valueOf(t));
	       System.out.println("Selected tray : " + selectedTray.toString());
	}
	
}


class PrintPage implements Printable {

    public int print(Graphics pg, PageFormat pf, int pageNum) throws PrinterException{
        // we print an empty page
        if (pageNum >= 1){
        	return Printable.NO_SUCH_PAGE;	
        }
        pg.drawString("Hello World!", 100, 100);
        return Printable.PAGE_EXISTS;
    }
}


