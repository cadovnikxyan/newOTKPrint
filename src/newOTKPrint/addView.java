package newOTKPrint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class addView {

	private final JFrame _frame= new JFrame("Добавление изделий");
	private final JPanel _panel= new JPanel();
	private final JComboBox _combobox= new JComboBox();
	private final JButton _button= new JButton();
	
	public addView(){
		_frame.setPreferredSize(new Dimension(500, 100));
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setResizable(false);
		_panel.setLayout(new BorderLayout());
		_panel.add(_combobox,BorderLayout.CENTER);
		_panel.add(_button,BorderLayout.EAST);
		_frame.getContentPane().add(_panel);
		_frame.pack();
		_frame.setLocationRelativeTo(null);
	}
	public void init(){
		_frame.setVisible(true);
		
	}
	
}
