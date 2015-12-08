package newOTKPrint;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SettingsView {

	private JFrame frame=new JFrame("Настройки");
	
	
	
	
	
	
	
	public void init(Object[] tray){
		
		
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
