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

    private String printerName = null;
    private AttributeSet aset = new HashAttributeSet();
    
    private Map<Integer, Media> trayMap = new HashMap<Integer, Media>(10);
    private MediaTray selectedTray=null;
    private ArrayList<String> trayArray=new ArrayList<String>();  
    
    private PrintService[] services=null;
    private PrintService defaultPrintService =null;
    private PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
    private String[] dataArray=null;
    
	public PrintTalon() {

		// get default printer
		defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();		
		// suggest the use of the default printer
		// if there is no input, use the default printer
		printerName = defaultPrintService.getName();
	    }


	public void init(){

        // the printer is selected    
        aset.add(new PrinterName(printerName, null));
        // selection of all print services
        services = PrintServiceLookup.lookupPrintServices(null, aset);
	}
	
	public void filTrayName(){
		
		   // we chose something compatible with the printable interface
		if(trayArray.size()!=0){
			trayArray.removeAll(trayArray);			
 		}
     for (PrintService service : services) {
         // we retrieve all the supported attributes of type Media
         // we can receive MediaTray, MediaSizeName, ...
         Object o = service.getSupportedAttributeValues(Media.class,
        		 		DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
         if (o != null && o.getClass().isArray()) {
             for (Media media : (Media[]) o) {           	
                 // we collect the MediaTray available
                 if (media instanceof MediaTray) {
                 	String buf=new String(media+":"+media.getValue())
                 									.replace("-Feeder", "");
                 	if(!trayArray.contains(buf)){
                 		trayArray.add(buf);	
                 	}
                     trayMap.put(media.getValue(), media);                                          
                 }
             }
         }
     }
	}
	public void createJob(){

		            // we have to add the MediaTray selected as attribute
		            attributes.add(selectedTray);
		            // we create the printer job, it print a specified document with a set of job attributes
		            DocPrintJob job = services[0].createPrintJob();		         
		            try {
		                // we create a document that implements the printable interface
		                Doc doc = new SimpleDoc(new PrintPage(dataArray),
		                		DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);

		                // we print using the selected attributes (paper tray)
		                job.print(doc, attributes);
		                
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
	
	
	public void setPrinterName(String printer){
		this.printerName="\\\\ps2\\"+printer.replaceFirst(printer.charAt(0)+"", "");
	}
	public void setMediaId(String id){

		this.selectedTray = (MediaTray) trayMap.get(Integer.valueOf(id));
	     
	}
	
	
	public ArrayList<String> getTrayArray(){
		return trayArray;
	}
	public String getPrintersName(){
		
		return defaultPrintService.getName();
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
	public void setStringData(String[] array){
		dataArray=array;
	}
		
}

class PrintPage implements Printable {

	private String[] dataArray=null;
	public PrintPage(String[] array){
		dataArray=array;
	}
    public int print(Graphics pg, PageFormat pf, int pageNum) throws PrinterException{
        // we print an empty page
        if (pageNum >= 1){
        	return Printable.NO_SUCH_PAGE;	
        }
        int y=100;
        for(int j=0;j<3;j++){
        	for(int i=0;i<dataArray.length;i++){
        		if(j==1){
        			pg.setFont(new Font("Times New Roman",Font.PLAIN,6));
        		}
        		if(dataArray.equals(null)){
        			pg.drawString("Hello!",100,y);
        		}else{
        			pg.drawString(dataArray[i].toString(), 100, y+=10);
        		}
        		
        	}
        }
        
        return Printable.PAGE_EXISTS;
    }
}


