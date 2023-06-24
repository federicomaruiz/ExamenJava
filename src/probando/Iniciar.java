/**
 * 
 */
package probando;

/**
 * @author federicoruiz
 * 20 jun 2023 19:34:36
 */
public class Iniciar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		Vista vista = new Vista();
		
		modelo.setVista(vista);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.setModelo(modelo);
		vista.setControlador(controlador);
		vista.setVisible(true);
		modelo.cargarTabla();

	}

}
