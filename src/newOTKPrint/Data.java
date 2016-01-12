package newOTKPrint;

public class Data {

	private String data[];
	private int x[]=new int[9];
	private int y[]=new int[9];
	private int font[]=new int[9];
	private static boolean flag=true;
	private static int dataPositionFlag=0;
	
	
	public Data(String[] _data, String[] XYFont){
		int f=0;
		int[] xyf= StringParserInt(XYFont);
			for(int i=0;i<xyf.length;i++){
				if(i==9){
					f=0;
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
//	public int getNextX(){
//		return this.x;
//	}
//	public int getNextY(){
//		return this.y;
//	}
//	public int getNextFont(){
//		return this.font;
//	}
//	
}
