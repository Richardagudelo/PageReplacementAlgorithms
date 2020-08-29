package views;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class JDAbout extends JDialog {

	private static final long serialVersionUID = 1L;

	public JDAbout(MainWindow mainWindow) {
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		setTitle("About");
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		setSize(180, 200);
		setLocationRelativeTo(mainWindow);
		
		add(new JLabel("Oscar Rojas"));
		add(new JLabel("Richard Agudelo"));
		add(new JLabel("Cristhian Chamorro"));
		
		setVisible(true);
	}
}