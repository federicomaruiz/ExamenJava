/**
 * 
 */
package examen2;

/**
 * @author federicoruiz
 * 19 jun 2023 16:42:51
 * 
 * Creo todos los objetos que voy a necesitar y hago los setters para que se conozcan entre ellos
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
		modelo.CargarDb();

	}

}
