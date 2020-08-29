package models.algorithms;

import models.PageReplacementAlgorithm;

public class CLOCK extends PageReplacementAlgorithm {

	@Override
	public void runPageReplacementAlgorithm() {
		runCLOCK();
	}

	/**
	 * Empieza la ejecucion del algoritmo de RELOJ
	 */
	private void runCLOCK() {
		int columnaEmpezar = completeFirstRows();
		columnaEmpezar += 1;
		completeCLOCk(columnaEmpezar);
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
	 * Completar el algoritmo de RELOJ
	 * 
	 * @param columnToStartFrom
	 *            desde donde comienza, basado en el metodo de llenado inicial
	 */
	private void completeCLOCk(int columnToStartFrom) {
		for (int column = columnToStartFrom; column < referenceChain.size(); column++) {
			if (isPageFailure(column, referenceChain.get(column))) {
				int positionRow = getMaximumOrMinimumUseTimeRowPosition(true);
				pageFrames[positionRow][column] = referenceChain.get(column);
				pageFrames[positionRow][pageFrames[0].length - 1] = 0;
			}
			plusOneToAllUseTime(column);
			copyColumnInNext(column);
		}
	}
}