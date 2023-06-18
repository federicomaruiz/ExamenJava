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
	 * Cargo los datos y limpio los input una ves cargados
	 * LLamo al modelo para modificar mi BD.
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
		boolean valido = modelo.validaNombre();
		return valido;
	}

	/*
	 * Creo una lista donde guardo el año de nacimiento de todos mis personas
	 * Luego lo convierto en un array de objetos para poder llamar al metodo GetEdadMedia
	 * que va a calcular la edad media y me devovlera un float.
	 */
	public float calcularEdad() {
		float res = 0f;
		int filas = modelo.getMiTabla().getRowCount();
		List<String> miLista = new ArrayList<>();
		for (int i = 0; i < filas; i++) {
			miLista.add((String) modelo.getMiTabla().getValueAt(i, 1));
		}
		Object [] arreglo = miLista.toArray(new Object[0]);
		res = modelo.getEdadMedia(arreglo);
		return res;
	}

	//
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
	 * En una lista voy a guardar todos los nombres que tengo en mi tabla
	 * luegeo convierto la lista en un array de objetos para enviarsela al metodo
	 * GetMasUsada que me va a devolver un char con la  letra mas usada
	 */
	public char letraRepetida() {
		int filas = modelo.getMiTabla().getRowCount();
		List<String> listaLetras = new ArrayList<>();
		for (int i = 0; i < filas; i++) {
			String letra = (String) modelo.getMiTabla().getValueAt(i, 0);
			letra = letra.toLowerCase();
			listaLetras.add(letra);
		}
		Object[] arreglo = listaLetras.toArray(new Object[0]);
		return modelo.getMasUsada(arreglo);
	}

}
