package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import controller.Control;
import events.Events;
import models.PageReplacementAlgorithm;
import models.algorithms.CLOCK;
import models.algorithms.FIFO;
import models.algorithms.LRU;

/**
 * Ventana principal de la aplicacion
 * 
 * @authors Oscar Rojas C, Cristhian Chamorro Vallejo, Richard Agudelo Contento
 */

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private OwnJTextField txtChain;
	private JSpinner spFrames;
	private OwnJRadioButton rbFifo, rbLru, rbClock;
	private OwnJButton btnRunAlg, btnInfo;
	private ButtonGroup buttonGroup;

	public MainWindow(Control control) {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Algoritmos de reemplazo de pagina");
		setSize(610, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(7, 1, 6, 7));

		initComponents(control);
		setVisible(true);
	}

	public PageReplacementAlgorithm getReplacementAlgorithm() {

		if (rbFifo.isSelected())
			return new FIFO();
		else if (rbLru.isSelected())
			return new LRU();
		else
			return new CLOCK();

	}
	
	public int getFramesToWork() {
		return (int) spFrames.getValue();
	}
	
	public ArrayList<Integer> getChain() {
		String text = txtChain.getText();
		text=text.replaceAll("\\s", "");
		System.out.println(text);
		String[] chain = text.split(",");
		ArrayList<Integer> referencesChain = new ArrayList<>();

		for (int i = 0; i < chain.length; i++) {
			referencesChain.add(Integer.parseInt(chain[i]));

		}
		return referencesChain;
	}

	private void initComponents(Control control) {

		txtChain = new OwnJTextField("Cadena de referencia (numero separados por \",\"  y \n sin espacios )");
		this.add(txtChain);

		spFrames = new JSpinner();
		spFrames.setModel(new SpinnerNumberModel(3, 1, 20, 1));
		spFrames.setBorder(new TitledBorder(null, "Numero de Marcos de pagina", TitledBorder.LEFT, TitledBorder.LEFT,
				new Font("Arial", 0, 20)));
		spFrames.setFont(new Font("Arial", 0, 25));
		this.add(spFrames);

		rbFifo = new OwnJRadioButton("FIFO");
		add(rbFifo);

		rbLru = new OwnJRadioButton("LRU");
		add(rbLru);

		rbClock = new OwnJRadioButton("RELOJ");
		add(rbClock);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rbFifo);
		buttonGroup.add(rbLru);
		buttonGroup.add(rbClock);

		btnRunAlg = new OwnJButton("Iniciar algoritmo", Events.STARTALGORITHM.toString(), control);
		
		add(btnRunAlg);

		btnInfo = new OwnJButton("Acerca de", Events.INFO.toString(), control);
		add(btnInfo);
	};

	public void showDialogAbout() {
		new JDAbout(this);
	}
}
