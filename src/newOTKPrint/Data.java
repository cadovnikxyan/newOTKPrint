package newOTKPrint;

public class Data {
	
	private String data[]= new String[3];
	private int x[]=new int[9];
	private int y[]=new int[9];
	private int font[]=new int[9];
	
	private static int dataPositionFlag=0;
	private static int xPositionFlag=0;
	private static int yPositionFlag=0;
	private static int fontPositionFlag=0;
	
	
	public Data(String[] _data, String[] XYFont){
		int f=0;
		int[] xyf= StringParserInt(XYFont);
			for(int i=0;i<xyf.length;i++){
				if(i==9){
					this.font[f++]=xyf[i];
				}
			}	
			int g=0;
			for(int j=8;j<xyf.length;j++){	
				if(g<9){
					this.x[g]= xyf[j];
					this.y[g]=xyf[j++];							
					g++;					
				}
			}
		this.data =_data;
	}
	
	private int[] StringParserInt(String[] xyf){
		int[] I=new int[xyf.length-1];
		for(int i=0;i<xyf.length-1;i++){
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
		}
		return null;
	}
	
	public int getNextX(){
		if(xPositionFlag<x.length){
			xPositionFlag++;
			return this.x[xPositionFlag-1];			
		}else{
			xPositionFlag=0;
		}
		return 0;
	}
	public int getNextY(){
		if(yPositionFlag<y.length){
			yPositionFlag++;
			return this.y[yPositionFlag-1];			
		}else{
			yPositionFlag=0;
		}
		return 0;
	}
	public int getNextFont(){
		if(fontPositionFlag<font.length){
			fontPositionFlag++;
			return this.font[fontPositionFlag-1];			
		}else{
			fontPositionFlag=0;
		}
		return 0;
	}
	
	public int length(){
		return this.data.length;
	}
	
}
