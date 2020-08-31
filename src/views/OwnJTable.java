package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que permite crear la tabla para mostrar los resultados
 * 
 * @authors Oscar Rojas C, Cristhian Chamorro Vallejo, Richard Agudelo Contento
 */
public class OwnJTable extends JTable {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmElements;

	public OwnJTable() {
		dtmElements = new DefaultTableModel();
		setModel(dtmElements);

		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
		getTableHeader().setBackground(Color.decode("#7AFC0D"));
		getTableHeader().setForeground(Color.BLACK);
		getTableHeader().setFont(new Font("Consolas", 0, 19));

		setFont(new Font("Consolas", 0, 17));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
	}

	/**
	 * Cambia los titulos de las columnas
	 * 
	 * @param columnIdentifiers los identificadores de columna, sera la cadena de
	 *                          referencia, cada numero de la cadena de referencia
	 *                          sera una columna de la tabla
	 */
	public void changeColumnIdentifiers(Object[] columnIdentifiers) {
		dtmElements.setColumnIdentifiers(columnIdentifiers);
	}

	/**
	 * Administra la informacion de la tabla, este metodo se llama cada vez que el
	 * usuario quiere ver los resultados, segun el algoritmo que escogio
	 * 
	 * @param pageFrames los resultados de los marcos de pagina, viene de el modelo
	 */
	public void manageTableData(int[][] pageFrames) {
		dtmElements.setRowCount(0);
		int numberOfRows = pageFrames.length - 1;
		int numberOfColumns = pageFrames[0].length - 1;

		addBlankRow(numberOfColumns);
		fillFramesRows(pageFrames, numberOfRows, numberOfColumns);
		addBlankRow(numberOfColumns);
		showPageFailures(pageFrames, numberOfRows, numberOfColumns);
	}

	/**
	 * Adiciona una fila en blanco a la tabla
	 * 
	 * @param numberOfColumns que tiene la tabla, para conocer cuantos blancos debe
	 *                        generar
	 */
	private void addBlankRow(int numberOfColumns) {
		dtmElements.addRow(new String[numberOfColumns]);
	}

	/**
	 * Agrega los valores de matriz de resultados, es decir los marcos y sus
	 * resultados a la tabla, cada marco en una fila
	 * 
	 * @param pageFrames      la matriz de marcos de pagina
	 * @param numberOfRows    numero de filas que tiene la matriz, removemos la
	 *                        ultima fila que es la del tiempo
	 * @param numberOfColumns numero de columnas que tiene la matriz, removemos la
	 *                        ultima columna que es la de los fallos de pagina
	 */
	private void fillFramesRows(int[][] pageFrames, int numberOfRows, int numberOfColumns) {
		String rowValues[] = new String[numberOfColumns];
		for (int i = 0; i < numberOfRows; i++) {
			rowValues = new String[numberOfColumns];
			for (int j = 0; j < numberOfColumns; j++) {
				if (pageFrames[i][j] == -1) {
					rowValues[j] = "";
				}
				else {
					rowValues[j] = pageFrames[i][j] + "";
				}
			}
			dtmElements.addRow(rowValues);
		}
	}

	/**
	 * Muestra los fallos de pagina de la ultima fila de la matriz, si es un 1 el
	 * valor encontrado entonces mostrara una x, si no, no mostrara nada
	 * 
	 * @param pageFrames      la matriz de marcos de pagina
	 * @param numberOfRows    numero de filas que tiene la matriz, removemos la
	 *                        ultima fila que es la del tiempo
	 * @param numberOfColumns numero de columnas que tiene la matriz, removemos la
	 *                        ultima columna que es la de los fallos de pagina
	 */
	private void showPageFailures(int[][] pageFrames, int numberOfRows, int numberOfColumns) {
		String pageFailures[] = new String[numberOfColumns];
		for (int k = 0; k < numberOfColumns; k++) {
			if (pageFrames[numberOfRows][k] == 1) {
				pageFailures[k] = "X";
			}
		}
		dtmElements.addRow(pageFailures);
	}
}