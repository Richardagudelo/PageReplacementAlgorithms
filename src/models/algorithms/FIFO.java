package models.algorithms;

import models.PageReplacementAlgorithm;

/**
 * Algoritmo FIFO (first in, first out)
 * 
 * @authors Oscar Rojas C, Cristhian Chamorro Vallejo, Richard Agudelo Contento
 */
public class FIFO extends PageReplacementAlgorithm {

	@Override
	public void runPageReplacementAlgorithm() {
		runFIFO();
	}

	private void runFIFO() {
		int columnaEmpezar = completeFirstRows();
		columnaEmpezar += 1;
		System.out.println(columnaEmpezar);
		showResults();
		System.out.println("-------------------------------------");
		completeFIFO(columnaEmpezar);
	}

	/**
	 * Primera parte Completa la matriz hasta que todas las filas esten llenas
	 * 
	 * @return el indice para comenzar la siguiente parte del algoritmo
	 */
	private int completeFirstRows() {
		int nextIndex = 0;
		for (int column = 0; column < referenceChain.size(); column++) {
			if (isPageFailure(column, referenceChain.get(column))) {
				for (int i = 0; i < pageFrames.length; i++) {
					if (pageFrames[i][column] == -1) {
						pageFrames[i][column] = referenceChain.get(column);
						break;
					}
				}
			}

			plusOneToAllUseTime(column);
			copyColumnInNext(column);
			if (getMaximumOrMinimumUseTime(false) > 0) {
				nextIndex = column;
				break;
			}
		}
		return nextIndex;
	}

	/**
	 * Completar el algoritmo FIFO
	 * 
	 * @param columnToStartFrom desde donde comienza, basado en el metodo de llenado
	 *                          inicial
	 */
	private void completeFIFO(int columnToStartFrom) {
		for (int column = columnToStartFrom; column < referenceChain.size(); column++) {
			if (isPageFailure(column, referenceChain.get(column))) {
				int positionRow = getMaximumOrMinimumUseTimeRowPosition(true);
				/*
				System.out.println("Columna trabajando " + column);
				System.out.println("Posicion donde encontro el maximo " + positionRow);
				System.out.println("Que hay en esa posicion " + pageFrames[positionRow][column]);
				*/
				pageFrames[positionRow][column] = referenceChain.get(column);
				pageFrames[positionRow][pageFrames[0].length - 1] = 0;
			}

			plusOneToAllUseTime(column);
			copyColumnInNext(column);
		}
	}
}