package newOTKPrint;

public class Data {
	
	private String data[]= new String[3];
	private int x[]=new int[9];
	private int y[]=new int[9];
	private int font[]=new int[9];
	
	private int dataPositionFlag=0;
	private int xPositionFlag=0;
	private int yPositionFlag=0;
	private int fontPositionFlag=0;
	
	
	public Data(String[] _data, String[] XYFont){
	
			int[] xyf= StringParserInt(XYFont);	
			int g=0;
			for(int j=0;j<9;j++){										
					this.x[g]= xyf[j];
					g++;									
			}	
			g=0;
			for(int j=9;j<18;j++){				
					this.y[g]=xyf[j];
					g++;
			}			
			g=0;
			for(int j=18;j<xyf.length-1;j++){
					this.font[g]=xyf[j];
					g++;
				
			}
		this.data =_data;
	}
	
	private int[] StringParserInt(String[] xyf){
		int[] I=new int[xyf.length];
		for(int i=0;i<xyf.length;i++){
			if(!xyf[i].equals("")){
				I[i]=Integer.parseInt(xyf[i]);
			}
		}
		return I;
	}
	
	public String getNextData(){
		if(dataPositionFlag<data.length){
			dataPositionFlag++;
			return this.data[dataPositionFlag-1];			
		}else{
			dataPositionFlag=0;
			return this.data[dataPositionFlag];
		}
		
	}
	
	public int getNextX(){
		if(xPositionFlag<x.length){
			xPositionFlag++;
			return this.x[xPositionFlag-1];			
		}else{
			xPositionFlag=0;
			return this.x[xPositionFlag];	
		}
	
	}
	public int getNextY(){
		if(yPositionFlag<y.length){
			yPositionFlag++;
			return this.y[yPositionFlag-1];			
		}else{
			yPositionFlag=0;
			return this.y[yPositionFlag];	
		}
		
	}
	public int getNextFont(){
		if(fontPositionFlag<font.length){
			fontPositionFlag++;
			return this.font[fontPositionFlag-1];			
		}else{
			fontPositionFlag=0;
			return this.font[fontPositionFlag];
		}
		
	}
	
	public int length(){
		return this.data.length;
	}
	
}
