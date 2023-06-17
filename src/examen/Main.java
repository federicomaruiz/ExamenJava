/**
 * 
 */
package examen;

/**
 * @author federicoruiz
 * 15 jun 2023 11:39:06
 * 
 * 
*/
public class Main {

	
	public static void main(String[] args) {
	
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		
		vista.setModelo(modelo);
		vista.setControlador(controlador);
		modelo.setVista(vista);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.setVisible(true);
		modelo.cargarDatos();

	}

}
