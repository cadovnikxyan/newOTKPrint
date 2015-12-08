package newOTKPrint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import javax.management.modelmbean.XMLParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class View  {
	
	final private JFrame frame= new JFrame("OTK_Print");

	final private Label label=new Label("Номер Талона",Label.CENTER);
	final private Label label1=new Label("Заводской номер",Label.CENTER);
	final private Label label2=new Label("Количество",Label.CENTER);
	final private Label label3=new Label("Наименование изделия",Label.CENTER);
	final private Label label4=new Label("Лоток");
	final private Label label5=new Label("Принтер");
	
	final private JButton button= new JButton();
	final private JButton button1= new JButton();
	
	final private JPanel panel= new JPanel();
	final private JPanel panel1= new JPanel();
	final private JPanel panel2= new JPanel();
	final private JPanel panel3= new JPanel();
	final private JPanel panel4= new JPanel();
	
	final private JSpinner spinner=new JSpinner();
	final private JSpinner spinner1=new JSpinner();
	final private JSpinner spinner2=new JSpinner();
	
	final private JComboBox combobox=new JComboBox(); 
	final private JComboBox comboboxTray= new JComboBox();
	final private JComboBox comboboxPrint= new JComboBox();
	
	final private JMenuBar menubar= new JMenuBar();
	
	private XmlParserSettings xml = null;
	
	final private PrintTalon talon= new PrintTalon();
	
	final private SettingsView settingsView= new SettingsView();
	
	
	
	
	public View() throws ParserConfigurationException, SAXException, IOException{
		frame.setPreferredSize(new Dimension(600, 170));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		talon.init();
		talon.filTrayName();
		
		
		
		menuInit();
		
		frame.setJMenuBar(menubar);
		
		drowPane();
		drowPane1();
		drowPane2();
		drowPane3();
		
		frame.getContentPane().add(panel,BorderLayout.WEST);
		frame.getContentPane().add(panel1,BorderLayout.CENTER);
		frame.getContentPane().add(panel2,BorderLayout.EAST);
		frame.getContentPane().add(panel3,BorderLayout.NORTH);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("2"+e.getKeyCode());
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.addWindowListener(new WindowListener() {
	
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				frame.setState(JFrame.NORMAL);
				try{
					xml= new XmlParserSettings("D://settings.xls");
					spinnerXmlInit();
				}catch(IOException e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Файл настроек не найден!");
					
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				Object[] options ={"Да","Нет"};
				int con=JOptionPane.showOptionDialog(arg0.getWindow(), "Закрыть программу?"
						,"Внимание!",JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,null,
						options,options[0] );
				if(con==0){
					arg0.getWindow().dispose();
					System.exit(0);
				}else if(con==1){
					
				}
				System.out.println(con);
				
			}
			
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void run() throws ParserConfigurationException, SAXException{
		frame.setVisible(true);
	}
	// ХЗ ВАЩЕ, НАДО ПОЙМАТЬ ИСКЛЮЧЕНИЯ ОТ ВСЕХ ВЬЮШЕК И ВЫДАТЬ ТОЛЬКО ОДНО ОКНО ДИАЛОГА
	private void spinnerXmlInit(){
		
		try {
			combobox.setModel(new DefaultComboBoxModel(xml.getStringTypes("string")));
			spinner.setValue(xml.getNumberValue("talon"));
			spinner1.setValue(xml.getNumberValue("work"));
			spinner2.setValue(xml.getNumberValue("number"));
			
		} 
		 catch (XMLParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ошибка чтения файла настроек!");
			
		} catch (NullPointerException ne){
			ne.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ошибка чтения файла настроек!");
		}
	}
	
	private void drowPane(){
		panel.setLayout(new BorderLayout());
		panel.add(label);
		panel.add(spinner,BorderLayout.SOUTH);
		alignmentSpinner(spinner);		
		panel.setBorder(new CompoundBorder(new LineBorder(Color.black), null));

	}
	private void drowPane1(){
		panel1.setLayout(new BorderLayout());
		panel1.add(label1);
		alignmentSpinner(spinner1);		
		panel1.add(spinner1,BorderLayout.SOUTH);
		panel1.setBorder(new CompoundBorder(new LineBorder(Color.black), null));
	
	}
	private void drowPane2(){
		panel2.setLayout(new BorderLayout());
		panel2.add(label2);
		alignmentSpinner(spinner2);
		panel2.add(spinner2,BorderLayout.SOUTH);
		panel2.setBorder(new CompoundBorder(new LineBorder(Color.black), null));

	}
	
	private void drowPane3() throws ParserConfigurationException, SAXException, IOException{

		combobox.setAlignmentX(Frame.CENTER_ALIGNMENT);
		combobox.setEditable(true);

		AutoCompletion.enable(combobox);
		panel3.setLayout(new BorderLayout());
		panel3.add(label3,BorderLayout.NORTH);
		panel3.add(combobox);
		panel3.setBorder(new CompoundBorder(new LineBorder(Color.black), null));
		button.setText("+");
		button1.setText("...");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(combobox.getSelectedItem());
			}
		});
		button.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				button.setToolTipText("Подсказка!");
			}
			
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		button1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("... clicked!");
				try {
					imageRead("D://1.png");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel4.add(button,BorderLayout.WEST);
		panel4.add(button1,BorderLayout.EAST);
		panel3.add(panel4,BorderLayout.AFTER_LINE_ENDS);
		
	}


	private void menuInit(){
		JMenu file=new JMenu("Файл");
		JMenuItem saveItem=new JMenuItem("Сохранить");
		JMenuItem settings= new JMenuItem("Настройки");
		JPanel panel= new JPanel();
		JButton _button= new JButton("Печать");
		ArrayList<String> array=talon.getTrayArray();
		ArrayList<String> parray=PrintTalon.getSystemPrintres();
		comboboxTray.setModel(new DefaultComboBoxModel(array.toArray()));
		comboboxPrint.setModel(new DefaultComboBoxModel(parray.toArray()));

		menubar.add(file);
		panel.add(_button);
		panel.add(label4);
		panel.add(comboboxTray);
		panel.add(label5);
		panel.add(comboboxPrint);
		panel.setLayout(new FlowLayout());
		menubar.add(panel);

		
		
		
		file.add(saveItem);
		file.add(settings);
		
		_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				talon.setMediaId(comboboxTray.getSelectedItem().toString());
				talon.setPrinterName(comboboxPrint.getSelectedItem().toString());
				
			}
		});
		settings.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				settingsView.run();
			}
		});
		saveItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int DialogKey=JOptionPane.showConfirmDialog(frame, "Сохранить?");
				switch(DialogKey){
				case 0:
					System.out.println("0");
					break;
				case 1:
					System.out.println("1");
					break;
				case 2:
					System.out.println("2");
				default:
					System.out.println("[[[");
					break;
				}
			}

		});
	}
	
	private void alignmentSpinner(JSpinner s){
		JComponent editor=s.getEditor();
		JSpinner.DefaultEditor spinnerEditor=(JSpinner.DefaultEditor)editor;
		spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
	}
	
	private void imageRead(final String filepath) throws IOException{
		 SwingUtilities.invokeLater(new Runnable() {
	          //  @Override
	            public void run() {
	            	ImageView img = new ImageView(filepath);
	                JPanel p = img;
	                JFrame f = new JFrame();
	                f.setContentPane(p);
	                f.setSize(img.getImageWidth()/4, img.getImageHeight()/4);
	                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                f.setVisible(true);
	                
	               
	            }
	        });
	}
	

static public void main(String[] args) throws ParserConfigurationException, 
SAXException, IOException, PrinterException{
	
	View view=new View();
	view.run();

 }
}