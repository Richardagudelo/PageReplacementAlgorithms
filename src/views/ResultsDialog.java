package views;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Dialogo para mostar los resultados del algoritmo en una tabla
 * 
 * @authors Oscar Rojas C, Cristhian Chamorro Vallejo, Richard Agudelo Contento
 */
public class ResultsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private OwnJTable jtResults;
	private OwnJLabel lblTitle, lblPageFailures;

	public ResultsDialog(JFrame mainWindow) {
		super(mainWindow);
		setModal(true);
		setSize(700, 450);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		getContentPane().setBackground(Color.WHITE);
		setTitle("Resultados del algoritmo");
		setLayout(new BorderLayout());
		setLocationRelativeTo(mainWindow);

		lblTitle = new OwnJLabel("Resultados algoritmo reemplazo de página");
		add(lblTitle, BorderLayout.PAGE_START);

		jtResults = new OwnJTable();
		add(new JScrollPane(jtResults), BorderLayout.CENTER);

		lblPageFailures = new OwnJLabel("Total fallos de página : 200");
		add(lblPageFailures, BorderLayout.PAGE_END);
	}

	public void changeColumnIdentifiers(Object[] columnIdentifiers) {
		jtResults.changeColumnIdentifiers(columnIdentifiers);
	}

	public void manageResultsData(String initialChain, int[][] pageFrames, String pageFailuresCount) {
		lblTitle.setText("<html><body><p style=\"text-align:center;\">" + lblTitle.getText() + "<br>" + initialChain
				+ "</p><br></body></html>");
		jtResults.manageTableData(pageFrames);
		lblPageFailures.setText("Total fallos de página :  " + pageFailuresCount);
	}
}