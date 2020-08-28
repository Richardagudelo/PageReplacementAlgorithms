package controller;

import java.util.ArrayList;
import models.PageReplacementAlgorithm;
import models.algorithms.FIFO;
import models.algorithms.LRU;

/**
 * Controlador de la aplicacion
 * 
 * @authors Oscar Rojas C, Cristhian Chamorro Vallejo, Richard Agudelo Contento
 */
public class Control {

	private PageReplacementAlgorithm pageReplacementAlgorithm;

	public Control() {
		ArrayList<Integer> auxList = new ArrayList<Integer>();
		auxList.add(1);
		auxList.add(2);
		auxList.add(3);
		auxList.add(3);
		auxList.add(5);
		auxList.add(1);
		auxList.add(2);
		auxList.add(2);
		auxList.add(6);
		auxList.add(2);
		auxList.add(1);
		auxList.add(5);
		auxList.add(7);
		auxList.add(6);
		auxList.add(3);
		
		pageReplacementAlgorithm = new LRU();
		pageReplacementAlgorithm.setAlgorithmValues(auxList, 4);
		
		pageReplacementAlgorithm.runPageReplacementAlgorithm();

		pageReplacementAlgorithm.showResults();
	}
}