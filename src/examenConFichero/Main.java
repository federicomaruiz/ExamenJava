package examenConFichero;

public class Main {

	public static void main(String[] args) {
		Controlador controlador=new Controlador();  // creamos los 3 elementos del MVC 
		Vista pantalla=new Vista();
		Modelo modelo=new Modelo();
		
		pantalla.setControlador(controlador);       // y hacemos setter de las asociaciones
		pantalla.setModelo(modelo);
		
		controlador.setPantalla(pantalla);
		controlador.setModelo(modelo);		
		// no hacemos setter de la vista en el modelo, porque no necesita comunicar hacia la vista
		
		pantalla.getFrame().setVisible(true);		// esto lo podríamos hacer desde el controlador (sería más limpio)
	}

}
