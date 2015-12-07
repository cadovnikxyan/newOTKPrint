package newOTKPrint;

import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SettingsView {

	private JFrame frame=new JFrame("Настройки");
	private JPanel panel= new JPanel();
	private JComboBox combobox= new JComboBox();
	
	public void init(Object[] tray){
		combobox.setModel(new DefaultComboBoxModel(tray));
		panel.add(combobox);
		frame.getContentPane().add(panel);
	}
	
	public void run(){
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				frame.setPreferredSize(new Dimension(500, 500));
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
	});
	
	
		}
	}
