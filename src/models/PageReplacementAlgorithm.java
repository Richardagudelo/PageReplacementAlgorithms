package models;

import java.util.ArrayList;

/**
 * Esta clase es una clase abstracta de la cual van a heredar las clases de los
 * distintos algoritmos a implementar, como FIFO, Reloj, y demas, obteniendo asi
 * atributos y metodos que se usan en comun para varios algoritmos.
 * 
 * @authors Oscar Rojas C, Cristhian Chamorro Vallejo, Richard Agudelo Contento
 */
public abstract class PageReplacementAlgorithm {

	/** La cadena inicial de referencia */
	public ArrayList<Integer> referenceChain;

	/** El numero final de fallos de pagina */
	private int totalPageFailures;

	/** Los marcos de pagina con los que se va a trabajar */
	public int[][] pageFrames;

	/**
	 * Actualiza o resetea los valores del algoritmo
	 * 
	 * @param referenceChain   la nueva cadena de referencia a trabajar
	 * @param pageFramesNumber el numero de marcos de pagina con los que se va a
	 *                         trabajar
	 */
	public void setAlgorithmValues(ArrayList<Integer> referenceChain, int pageFramesNumber) {
		this.referenceChain = referenceChain;
		this.totalPageFailures = 0;

		// Filas x columnas
		// (numero de paginas (3) + 1) X (tamaño de la cadena de referencia + 1)
		this.pageFrames = new int[pageFramesNumber + 1][referenceChain.size() + 1];
		fillPageFramesMatrixWithMinus1();
	}

	/**
	 * Metodo que tendra que implementar cada uno de los algoritmos. Sirve para
	 * correr el algoritmo
	 */
	public abstract void runPageReplacementAlgorithm();

	/**
	 * Muestra los resultados del algoritmo por terminal
	 */
	public void showResults() {
		for (int numb : referenceChain) {
			System.out.print(numb + "\t");
		}
		System.out.println();
		String message = "";
		for (int i = 0; i < pageFrames.length - 1; i++) {
			for (int j = 0; j < pageFrames[0].length; j++) {
				message += pageFrames[i][j] + "\t";
			}
			message += "\n";
		}
		System.out.println(message);
		message = "";
		for (int k = 0; k < pageFrames[0].length; k++) {
			message += pageFrames[pageFrames.length - 1][k] == 1 ? "X" : "\t";
		}
		System.out.println(message);
		calculateTotalPageFailures();
		System.out.println("\n" + "Numero total de fallos de pagina: " + totalPageFailures);
	}

	/**
	 * Busca si el valor a ingresar ya existe en el marco de pagina ingresado
	 * 
	 * @param columnSearch columna a buscar, marco de pagina
	 * @param shiftNumber  numero que se va a insertar
	 * @return si hubo fallo de pagina (cuando el numero no estaba)
	 */
	public boolean isPageFailure(int columnSearch, int shiftNumber) {
		for (int i = 0; i < pageFrames.length; i++) {
			if (pageFrames[i][columnSearch] == shiftNumber) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Llenamos la matriz al iniciar con el valor -1, para conocer que valor esta
	 * vacio por defecto, ya que si la dejamos en 0, parecera que ya habia algo y
	 * generara errores
	 */
	private void fillPageFramesMatrixWithMinus1() {
		for (int i = 0; i < pageFrames.length; i++) {
			for (int j = 0; j < pageFrames[0].length; j++) {
				pageFrames[i][j] = -1;
			}
		}
	}

	/**
	 * Calcula el numero total de fallos de pagina, haciendo un recuento de los
	 * encontrados en la matriz
	 */
	private void calculateTotalPageFailures() {
		for (int k = 0; k < pageFrames[0].length; k++) {
			totalPageFailures += pageFrames[pageFrames.length - 1][k] == 1 ? 1 : 0;
		}
	}

	/**
	 * Getter de referenceChain
	 * 
	 * @return la cadena de referencia original, planteada por el usuario
	 */
	public ArrayList<Integer> getReferenceChain() {
		return referenceChain;
	}

	/**
	 * Getter de el numero total de fallos de pagina
	 * 
	 * @return el numero total de fallos de pagina, calculandolo antes
	 */
	public int getTotalPageFailures() {
		calculateTotalPageFailures();
		return totalPageFailures;
	}

	/**
	 * Getter de marcos de pagina
	 * 
	 * @return los marcos de pagina usados en el algoritmo
	 */
	public int[][] getPageFrames() {
		return pageFrames;
	}
}