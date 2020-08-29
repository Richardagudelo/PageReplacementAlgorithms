package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class OwnJTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public OwnJTextField(String borderTitle) {

		setBorder(new TitledBorder(null, borderTitle, TitledBorder.LEFT, TitledBorder.LEFT, new Font("Arial", 0, 20)));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setFont(new Font("Arial", 0, 25));
		
	}
}