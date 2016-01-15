package newOTKPrint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class SettingsView {

	private JFrame frame=new JFrame("Настройки");
	
	private JPanel panel= new JPanel();
	private JPanel panel1= new JPanel();
	private JPanel panel2= new JPanel();
	
	private XmlParserSettings xml=null;
	

	public SettingsView(XmlParserSettings _xml){
		setXml(_xml);
	}
	public SettingsView(){
		
	}
	
	
	public XmlParserSettings getXml() {
		return xml;
	}
	public void setXml(XmlParserSettings xml) {
		this.xml = xml;
	}
	private JLabel[] createLabels(String[] names, int number){
		if(names.length!=number){
			return null;
		}
		JLabel[] labels=new JLabel[number] ;
		for(int i=0;i<number;i++){
			labels[i]=new JLabel(names[i]);
		}
		
		return labels;
		
	}
	
	public void init(){
		String[] names=new String[9];
		for(int i=0;i<9;i++){
			names[i]="Координата X "+i;
		}
		String[] names1=new String[9];
		for(int i=0;i<9;i++){
			names1[i]="Координата Y "+i;
		}
		String[] names2=new String[9];
		for(int i=0;i<9;i++){
			names2[i]="Шрифт "+i;
		}
		
		JLabel[] labels=createLabels(names,9);
		JLabel[] labels1=createLabels(names1,9);
		JLabel[] labels2=createLabels(names2,9);
		
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
			
			panel.add(new JSpinner());
			panel1.add(new JSpinner());
			panel2.add(new JSpinner());
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
				frame.setPreferredSize(new Dimension(600, 400));
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
	});
	
	
		}
	
	public static void main(String[] args){
		SettingsView view=new SettingsView();
		view.run();
	}
	}
