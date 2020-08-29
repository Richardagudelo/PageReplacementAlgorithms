package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import events.Events;
import models.*;
import views.MainWindow;

/**
 * Controlador de la aplicacion
 * 
 * @authors Oscar Rojas C, Cristhian Chamorro Vallejo, Richard Agudelo Contento
 */
public class Control implements ActionListener {

	private PageReplacementAlgorithm pageReplacementAlgorithm;
	private MainWindow mainWindow;

	public Control() {
		mainWindow = new MainWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (Events.valueOf(e.getActionCommand())) {
		case EXIT:
			System.exit(0);
			break;
		case INFO:
			mainWindow.showDialogAbout();
			break;
		case STARTALGORITHM:
			startAlgorithm();
			break;
		}

	}

	private void startAlgorithm() {
		pageReplacementAlgorithm = mainWindow.getReplacementAlgorithm();
		pageReplacementAlgorithm.setAlgorithmValues(mainWindow.getChain(), mainWindow.getFramesToWork());
		pageReplacementAlgorithm.runPageReplacementAlgorithm();
		pageReplacementAlgorithm.showResults();
	}
}
