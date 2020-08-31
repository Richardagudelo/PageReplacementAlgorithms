package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class JDAbout extends JDialog {

	private static final long serialVersionUID = 1L;

	public JDAbout(MainWindow mainWindow) {
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		setTitle("About");
		setLayout(new BorderLayout());
		setSize(500, 250);
		setLocationRelativeTo(mainWindow);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		setResizable(false);

		String info = "<html>"
				+ 		"<body>"
				+ 			"<p style=\"text-align:center;font-size:14\">Integrantes"
				+ 			"<br>"
				+ 			"Cristhian Andres Chamorro"
				+ 			"<br>"
				+ 			"Richard Agudelo Contento"
				+ 			"<br>"
				+ 			"Oscar Augusto Rojas Cruz"
				+ 			"<br>"
				+ 			"<br>"
				+ 			"Repositorio Código GitHub : "
				+ 			"<br>"
				+ 			"</p>"
				+ 		"</body>"
				+ 	"</html>";
		
		add(new OwnJLabel(info), BorderLayout.PAGE_START);	
		
		OwnJTextField repo = new OwnJTextField("");
		repo.setFont(new Font("Arial", 2, 15));
		repo.setForeground(Color.BLUE);
		repo.setText("https://github.com/Richardagudelo/PageReplacementAlgorithms");
		add(repo, BorderLayout.CENTER);

		setVisible(true);
	}
}