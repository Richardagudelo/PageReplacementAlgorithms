package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class OwnJLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public OwnJLabel(String text) {
		setText(text);
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setFont(new Font("High Tower Text", 1, 25));
		setHorizontalAlignment(JLabel.CENTER);
	}
}