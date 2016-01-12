package newOTKPrint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddView extends AbstractView{

	private final JFrame frame= new JFrame("Добавление изделий");
	private final JPanel panel= new JPanel();
	private final JComboBox combobox= new JComboBox();
	private final JButton button= new JButton();
	
	public AddView(){
		frame.setPreferredSize(new Dimension(500, 70));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		panel.setLayout(new BorderLayout());
		panel.add(combobox,BorderLayout.CENTER);
		combobox.setEditable(true);
		panel.add(button,BorderLayout.EAST);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	public void init(){
		frame.setVisible(true);
	
		
	}
	
}
