package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;

public class OwnJRadioButton extends JRadioButton {

	private static final long serialVersionUID = 1L;

	public OwnJRadioButton(String text) {
		setText(text);
		setBackground(Color.WHITE);
		setFont(new Font("Arial", 0, 25));
		setBorderPainted(false);
		setForeground(Color.BLACK);
	}
}