package examenFichero;

public class Main {

	public static void main(String[] args) {

		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador();
		
		modelo.setVista(vista);
		
		vista.setModelo(modelo);
		vista.setControlador(controlador);
		
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		
		controlador.iniciarPrograma();
	}

}
