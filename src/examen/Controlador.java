/**
 * 
 */
package examen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author federicoruiz 15 jun 2023 11:46:54
 */
public class Controlador {

	private Vista vista;
	private Modelo modelo;

	/**
	 * @return the vista
	 */
	public Vista getVista() {
		return vista;
	}

	/**
	 * @param vista the vista to set
	 */
	public void setVista(Vista vista) {
		this.vista = vista;
	}

	/**
	 * @return the modelo
	 */
	public Modelo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * Cargo los datos y limpio los input
	 * 
	 */
	public void cargarDatos() {
		String user = vista.getTxtNombre().getText();
		String año = vista.getTxtAño().getText();
		modelo.añadir(user, año);
		modelo.cargarDatos();
		vista.getTxtNombre().setText("");
		vista.getTxtAño().setText("");

	}


	/**
	 * Valido el nombre con el metodo matches que solo hayan caracters alfabeticos y
	 * espacios en blanco
	 */
	public boolean validaNombre() {
		String user = vista.getTxtNombre().getText();
		if (user.matches("[a-zA-Zá-úÁ-Ú\s]+")) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Calculo la edad media accedo a la segunda columna que es donde estan los años
	 * los sumo y los divido por el total de personas, luego le resto el año actual para sacar
	 * la media de edad.
	 * */
	public float calcularEdad() {
		float res = 0f;
		int suma = 0;
		int filas = modelo.getMiTabla().getRowCount();
		for (int i = 0; i < filas; i++) {
			String años = (String) modelo.getMiTabla().getValueAt(i, 1);
			suma += Integer.parseInt(años);
		}
		suma = suma / filas;
		res = 2023 - suma;
		return res;
	}

	/**
	 * Para la edad compruebo que la fecha de nacimiento tenga 4 digitos
	 */
	public boolean comprobarEdad() {
		String edad = vista.getTxtAño().getText();
		if (edad.matches("[0-9]{4}")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * En una lista voy a guardar todas las primeras letras de todos los nombres,
	 * Luego con un bucle anidado voy recorriendo la lista para saber cual es la letra que mas se repite
	 *
	 */
	public char letraRepetida() {
		modelo.getMasUsada(null);
		int filas = modelo.getMiTabla().getRowCount();
		char letraRepetida;
		String letra;
		List<String> listaLetras = new ArrayList<>();
		String masRep = "";
		int count = 0;
		int max = 0;
		for (int i = 0; i < filas; i++) {
			letra = (String) modelo.getMiTabla().getValueAt(i, 0);
			letraRepetida = letra.toLowerCase().charAt(0);
			letra = String.valueOf(letraRepetida);
			listaLetras.add(letra);
		}
		for (int i = 0; i < listaLetras.size(); i++) {
			count = 0;
			for (int j = 0; j < listaLetras.size(); j++) {
				String letraUno = listaLetras.get(i);
				String letraDos = listaLetras.get(j);

				if (letraUno.equals(letraDos)) {
					count++;
				}
				if (count > max) {
					max = count;
					masRep = listaLetras.get(i);
				}

			}
		}
		letraRepetida = masRep.charAt(0);
		return letraRepetida;
	}


}
