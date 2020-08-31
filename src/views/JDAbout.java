package views;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class JDAbout extends JDialog {

	private static final long serialVersionUID = 1L;

	public JDAbout(MainWindow mainWindow) {
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		setTitle("About");
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		setSize(220, 250);
		setLocationRelativeTo(mainWindow);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		setResizable(false);

		add(new JLabel("Integrantes"));
		add(new JLabel("============================="));
		add(new JLabel("Oscar Augusto Rojas Cruz"));
		add(new JLabel("Richard Agudelo Contento"));
		add(new JLabel("Cristhian Andres Chamorro"));

		setVisible(true);
	}
}