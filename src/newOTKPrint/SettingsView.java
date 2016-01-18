package newOTKPrint;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SettingsView {

	

	private JFrame frame=new JFrame("Настройки");
	
	private JPanel panel= new JPanel();
	private JPanel panel1= new JPanel();
	private JPanel panel2= new JPanel();
	
	private XmlParserSettings xml=null;
	private Data data=null;
	private String[] countName=new String[9];

	public SettingsView(XmlParserSettings _xml,Data _data) throws Exception{
		setXml(_xml);
		setData(_data);
	}
	public SettingsView(){
		
	}
	
	public XmlParserSettings getXml() {
		return xml;
	}
	public void setXml(XmlParserSettings xml) {	
		this.xml = xml;
	}
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
	private void fillCountNameArray(){
		countName[0]="первой";
		countName[1]="второй";
		countName[2]="третей";
		countName[3]="четвертой";
		countName[4]="пятой";
		countName[5]="шестой";
		countName[6]="седьмой";
		countName[7]="восьмой";
		countName[8]="девятой";
		
	}
	private JLabel[] createLabels(String[] names, int number){
		if(names.length!=number){
			return null;
		}
		JLabel[] labels=new JLabel[number] ;
		for(int i=0;i<number;i++){
			labels[i]=new JLabel("<html>"+names[i]+"</html>");
			labels[i].setBorder(new CompoundBorder(new LineBorder(Color.black), null));
		}
		
		return labels;
	}
	private JSpinner[] createSpinners(int numbers){
		JSpinner[] spinner= new JSpinner[numbers];
		for(int i=0;i<numbers;i++){
			spinner[i]=new JSpinner();
			spinner[i].setBorder(new CompoundBorder(new LineBorder(Color.black), null));
			spinner[i].addChangeListener(new ChangeListener() {
				
				public void stateChanged(ChangeEvent arg0) {
					//TODO
					JSpinner b=(JSpinner) arg0.getSource();
					System.out.println(b.getValue());
					
				}
			});
		}
		return spinner;
		
	}
	
	public void init(){
		fillCountNameArray();
		String[] names=new String[9];
		String[] names1=new String[9];
		String[] names2=new String[9];
		for(int i=0;i<9;i++){
			names[i]="Координата X "+this.countName[i]+" надписи";
			names1[i]="Координата Y "+this.countName[i]+" надписи";
			names2[i]="Шрифт "+this.countName[i]+" надписи";
		}
		
		JLabel[] labels=createLabels(names,9);
		JLabel[] labels1=createLabels(names1,9);
		JLabel[] labels2=createLabels(names2,9);
		
		JSpinner[] spinner=createSpinners(9);
		JSpinner[] spinner1=createSpinners(9);
		JSpinner[] spinner2=createSpinners(9);
		
		panel.setLayout(new GridLayout(9,0));
		panel1.setLayout(new GridLayout(9,0));
		panel2.setLayout(new GridLayout(9,0));
		
		panel.setBorder(new CompoundBorder(new LineBorder(Color.black), null));
		panel1.setBorder(new CompoundBorder(new LineBorder(Color.black), null));
		panel2.setBorder(new CompoundBorder(new LineBorder(Color.black), null));
		for(int i=0;i<9;i++){			
		
			panel.add(labels[i]);
			panel1.add(labels1[i]);
			panel2.add(labels2[i]);
			
			panel.add(spinner[i]);
			panel1.add(spinner1[i]);
			panel2.add(spinner2[i]);
		}
		
		
	}
	
	public void run(){
		SwingUtilities.invokeLater(new Runnable() {			
			public void run() {
				init();
				frame.setLayout(new GridLayout(1,3));
				frame.getContentPane().add(panel);
				frame.getContentPane().add(panel1);
				frame.getContentPane().add(panel2);
				frame.setPreferredSize(new Dimension(600, 500));
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
	});
	
	
		}
	
	public static void main(String[] args){
		SettingsView view=new SettingsView();
		view.run();
	}
	
	}
