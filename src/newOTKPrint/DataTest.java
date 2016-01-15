package newOTKPrint;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataTest {

	Data data;
	String[] dataS=new String[3];
	String[] XYFont=new String[27];
	String b="<int>470</int> <int>275</int> <int>255</int> <int>335</int> <int>200</int> <int>253</int> <int>586</int> <int>450</int> <int>500</int> <int>43</int> <int>95</int> <int>118</int> <int>657</int> <int>675</int> <int>710</int> <int>657</int> <int>675</int> <int>710</int> <int>10</int> <int>8</int> <int>8</int> <int>8</int> <int>6</int> <int>8</int> <int>8</int> <int>6</int> <int>8</int>";
	String[] s=new String[27];
	
	@Test
	public void test() {
		
		dataS[0]="Барьер искробезопасности БИБ1";
		dataS[1]="1";
		dataS[2]="2";
		
		XYFont[0]="470";
		XYFont[1]="275";
		XYFont[2]="255";
		XYFont[3]="335";
		XYFont[4]="200";
		XYFont[5]="253";
		XYFont[6]="586";
		XYFont[7]="450";
		XYFont[8]="500";
		XYFont[9]="43";
		XYFont[10]="95";
		XYFont[11]="118";
		XYFont[12]="657";
		XYFont[13]="675";
		XYFont[14]="710";
		XYFont[15]="657";
		XYFont[16]="675";
		XYFont[17]="710";
		XYFont[18]="10";
		XYFont[19]="8";
		XYFont[20]="8";
		XYFont[21]="8";
		XYFont[22]="6";
		XYFont[23]="8";
		XYFont[24]="8";
		XYFont[25]="6";
		XYFont[26]="8";
		
//		s=b.replace("<int>", "").replace("</int>", "").split(" ");
//		for(int i=0;i<27;i++){
//			System.out.println("XYFont["+i+"]="+"\""+s[i]+"\""+";");			
//		}
		try{
			data= new Data(dataS,XYFont);
			
		}catch(NullPointerException e){
			fail("test if failed!");
		}
		for(int i=0;i<9;i++){
			
			System.out.println("Font: "+data.getNextFont());
//			System.out.println("X: "+data.getNextX());
//			System.out.println("Y: "+data.getNextY());			
		}
		
		
	}

}
