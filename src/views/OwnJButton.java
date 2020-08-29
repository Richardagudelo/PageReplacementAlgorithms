package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import controller.Control;

public class OwnJButton  extends JButton{

	private static final long serialVersionUID = 1L;

	public OwnJButton(String text,String actionCommand, Control control) {
		setText(text);
		setBackground(Color.decode("#7AFC0D"));
		setFont(new Font("Arial",0, 20));
		setBorderPainted(false);
		setForeground(Color.BLACK);
		setActionCommand(actionCommand);
		addActionListener(control);
	}
}