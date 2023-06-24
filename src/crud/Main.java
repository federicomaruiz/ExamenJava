/**
 * 
 */
package crud;

/**
 * @author federicoruiz
 * 21 jun 2023 15:17:01
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		Vista vista = new Vista();
		Vfichero Vfichero = new Vfichero();
		
		modelo.setVista(vista);
		modelo.setVfichero(Vfichero);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		controlador.setvFichero(Vfichero);
		vista.setModelo(modelo);
		vista.setControlador(controlador);
		vista.setVisible(true);
		vista.setVfichero(Vfichero);
		Vfichero.setModelo(modelo);
		Vfichero.setControlador(controlador);
		Vfichero.setVista(vista);
		controlador.iniciarApp();
	}

}
