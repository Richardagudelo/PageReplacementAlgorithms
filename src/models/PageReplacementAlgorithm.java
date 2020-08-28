package models;

import java.util.ArrayList;
import java.util.Comparator;

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
		System.out.println("TIME\n");

		String message = "";
		for (int i = 0; i < pageFrames.length - 1; i++) {
			for (int j = 0; j < pageFrames[0].length; j++) {
				message += pageFrames[i][j] + "\t";
			}
			message += "\n";
		}
		System.out.println(message);

		message = "";
		for (int k = 0; k < pageFrames[0].length - 1; k++) {
			message += pageFrames[pageFrames.length - 1][k] == 1 ? "X\t" : "\t";
		}
		System.out.println(message);
		calculateTotalPageFailures();
		System.out.println("Numero total de fallos de pagina: " + totalPageFailures);
	}

	/**
	 * Busca si el valor a ingresar ya existe en el marco de pagina ingresado, si
	 * hay fallo de pagina entonces lo crea
	 * 
	 * @param columnSearch columna a buscar, marco de pagina
	 * @param shiftNumber  numero que se va a insertar
	 * @return si hubo fallo de pagina (cuando el numero no estaba)
	 */
	public boolean isPageFailure(int columnSearch, int shiftNumber) {
		for (int i = 0; i < pageFrames.length - 1; i++) {
			if (pageFrames[i][columnSearch] == shiftNumber) {
				return false;
			}
		}
		pageFrames[pageFrames.length - 1][columnSearch] = 1;
		return true;
	}

	/**
	 * Obtiene el mayor o menor tiempo de uso entre los marcos, dependiendo de la
	 * variable searchMax
	 * 
	 * @param searchMax es true si quiere buscar el que mas tiempo ha estado o false
	 *                  si quiere encontrar el que menos tiempo ha estado
	 * @return el resultado segun lo pedido
	 */
	public int getMaximumOrMinimumUseTime(boolean searchMax) {
		ArrayList<Integer> useTimes = new ArrayList<Integer>();
		for (int i = 0; i < pageFrames.length - 1; i++) {
			useTimes.add(pageFrames[i][pageFrames[0].length - 1]);
		}

		useTimes.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer number1, Integer number2) {
				return number1 - number2; // orden descendente
			}
		});

		int result = searchMax ? useTimes.size() - 1 : 0;
		return useTimes.get(result);
	}

	/**
	 * Busca la posicion en la matriz donde encontro el mayor o menor tiempo segun
	 * el parametro
	 * 
	 * @param searchMax busca el maximo(true) o el minimo(false)
	 * @return la posicion donde lo encontro, en la matriz
	 */
	public int getMaximumOrMinimumUseTimeRowPosition(boolean searchMax) {
		int position = 0;
		int maximumOrMinimumValue = this.getMaximumOrMinimumUseTime(searchMax);

		for (int i = 0; i < pageFrames.length - 1; i++) {
			if (pageFrames[i][pageFrames[0].length - 1] == maximumOrMinimumValue) {
				position = i;
				break;
			}
		}
		return position;
	}

	/**
	 * Copia la columna actual en la siguiente, si es que existe en la matriz
	 * 
	 * @param currentColumn columna en la que se encuentra actualmente
	 */
	public void copyColumnInNext(int currentColumn) {
		int nextColumn = currentColumn + 1;
		if (nextColumn < pageFrames[0].length - 1) {
			for (int i = 0; i < pageFrames.length - 1; i++) {
				pageFrames[i][nextColumn] = pageFrames[i][currentColumn];
			}
		}
	}

	/**
	 * Le suma 1 a todos los tiempos de uso mayores que 0, es decir que hay algo en
	 * el marco
	 */
	public void plusOneToAllUseTime(int currentColumn) {
		for (int i = 0; i < pageFrames.length - 1; i++) {
			if (pageFrames[i][currentColumn] >= 0) {
				pageFrames[i][pageFrames[0].length - 1] += 1;
			}
		}
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
		for (int i = 0; i < pageFrames.length; i++) {
			pageFrames[i][pageFrames[0].length - 1] = 0;
		}
	}

	/**
	 * Calcula el numero total de fallos de pagina, haciendo un recuento de los
	 * encontrados en la matriz
	 */
	private void calculateTotalPageFailures() {
		totalPageFailures = 0;
		for (int k = 0; k < pageFrames[0].length - 1; k++) {
			if (pageFrames[pageFrames.length - 1][k] == 1) {
				totalPageFailures += 1;
			}
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