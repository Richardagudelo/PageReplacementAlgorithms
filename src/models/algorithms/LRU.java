package models.algorithms;

import models.PageReplacementAlgorithm;

public class LRU extends PageReplacementAlgorithm {

	@Override
	public void runPageReplacementAlgorithm() {
		runLRU();
	}

	private void runLRU() {
		int columnaEmpezar = completeFirstRows();
		columnaEmpezar += 1;
		completeLRU(columnaEmpezar);
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
	private void completeLRU(int columnToStartFrom) {
		for (int column = columnToStartFrom; column < referenceChain.size(); column++) {
			if (isPageFailure(column, referenceChain.get(column))) {
				int positionRow = getMaximumOrMinimumUseTimeRowPosition(true);
				pageFrames[positionRow][column] = referenceChain.get(column);
				pageFrames[positionRow][pageFrames[0].length - 1] = 0;
			} else {
				resetTimeOfNotPageFailure(column, referenceChain.get(column));
			}

			plusOneToAllUseTime(column);
			copyColumnInNext(column);
		}
	}

	/**
	 * Resetea el tiempo de la fila cuando no hay fallo de pagina Si llego el mismo
	 * numero en la columna como en la posicion de la lista
	 * 
	 * @param column         donde buscara el numero de la cadena de referencia
	 * @param numberToSearch numero a buscar en la matriz [filas] [column]
	 */
	private void resetTimeOfNotPageFailure(int column, int numberToSearch) {
		for (int i = 0; i < pageFrames.length - 1; i++) {
			if (pageFrames[i][column] == numberToSearch) {
				pageFrames[i][pageFrames[0].length - 1] = 0;
			}
		}
	}
}