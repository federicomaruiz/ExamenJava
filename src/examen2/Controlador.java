/**
 * 
 */
package examen2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author federicoruiz 19 jun 2023 16:42:28
 */
public class Controlador extends Info implements Gestionar {

	private Vista vista;
	private Modelo modelo;
	private String nombre;
	private String anoNacimiento;
	private final int ANOACTUAL = 2023;
	List<Character> misLetras = new ArrayList<>();

	
	/**
	 * 
	 */
	public Controlador() {
		super();
	}

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

	/*
	 * Paso todas las edades de mi tabla a una array de objetos para luego utilizar
	 * el metodo implementado llamo al metodo este me devuelve la edad media, lo
	 * convierto en string para luego introducirlo en el campo de mi interfaz
	 * grafica
	 */
	public void edadMedia() {
		int numFilas = modelo.getMiTabla().getRowCount();
		Object[] listaEdad = new Object[numFilas];
		for (int i = 0; i <= numFilas - 1; i++) {
			listaEdad[i] = modelo.getMiTabla().getValueAt(i, 1);
		}
		String edadMedia = String.valueOf(getEdadMedia(listaEdad));

		vista.getTxtEdadMedia().setText(edadMedia);
	}

	@Override
	public float getEdadMedia(Object[] miLista) {
		int total = 0;
		int numPersonas = modelo.getMiTabla().getRowCount();
		float edadMedia;

		for (Object ano : miLista) {
			total += Integer.parseInt((String) ano);
			System.out.println(total);
		}
		edadMedia = ANOACTUAL - (total / numPersonas);
		return edadMedia;
	}

	/*
	 * Voy a crear un objeto donde voy a tener todas las letras luego llamo al metodo
	 * getMasUsada para que me devuelva la letra mas usada luego la añado a mi interfaz grafica
	 */

	public void letraMasUsada() {
		int numFilas = modelo.getMiTabla().getRowCount();
		Object[] listaNombres = new Object[numFilas];
		for (int i = 0; i <= numFilas - 1; i++) {
			listaNombres[i] = modelo.getMiTabla().getValueAt(i, 0);
		}
		String letraRep =  String.valueOf(getMasUsada(listaNombres));
		vista.getTxtLetraRep().setText(letraRep);
	}

	/*
	 * Creo una lista de tipo character con la primera letra de todos los nombres luego con un bucle
	 * aninado voy recorriendo y comparando cual es la letra que mas se repite devuelvo el char mas repetido
	 * */
	@Override
	public char getMasUsada(Object[] miLista) {

		char letraMasRep = '\0';
		int count;
		int maxRep = 0;
		for (Object nombre : miLista) {
			String nom = (String) nombre;
			misLetras.add(nom.charAt(0));
		
		}

		for (int i = 0; i < misLetras.size(); i++) {
			count = 0;
			for (int j = 0; j < misLetras.size(); j++) {
				char letraUno = misLetras.get(i);
				char letraDos = misLetras.get(j);
				
				if(letraUno == letraDos) {
					count++;
				}
				if(count > maxRep) {
					maxRep = count;
					letraMasRep = letraUno;
				}
			}
		}
		return letraMasRep;
	}

	/*
	 * Compruebo que el usuario ingrese solo letras mayusculas, minusculas, con
	 * acento y tambien puede introducir espacios en blanco
	 */
	@Override
	boolean validaNombre() {
		nombre = vista.getTxtNombre().getText();
		if (nombre.matches("[a-zA-Zá-úÁ-Ú\s]+")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Compruebo que la fecha de nacimiento contenga solo 4 digitos
	 */
	public boolean validarAno() {
		anoNacimiento = vista.getTxtAño().getText();
		if (anoNacimiento.matches("[0-9]{4}")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Muestro al usuario un mensaje si el usuario fue añadido o no Si fue añadido
	 * recargo la tabla para actualizarla
	 */
	public void cargarUsuario() {
		int resultado = modelo.cargarUsuario();
		if (resultado > 0) {
			modelo.cargarTabla();
			vista.getLblUsuarioAnadido().setVisible(true);
			vista.getLblUsuarioAnadidoErr().setVisible(false);
		} else {
			vista.getLblUsuarioAnadido().setVisible(false);
			vista.getLblUsuarioAnadidoErr().setVisible(true);
		}
	}

}
