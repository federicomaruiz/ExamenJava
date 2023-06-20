package examenFichero;

public class Controlador extends Info {

	private Modelo modelo;
	private Vista vista;

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

	public void agregaRegistro(String nombre, String fecha) {
		modelo.agregarRegistro(nombre, fecha);
		actualizarVista();
	}

	private void actualizarVista() {
		vista.setModeloTabla(modelo.getModeloTabla());
		vista.setEdadMedia(modelo.getEdadMedia(modelo.getMiLista()));
		vista.setMasUsada(modelo.getMasUsada(modelo.getMiLista()));
		vista.limpiarPantalla();
	}

	boolean validaNombre() {	
		return modelo.validaNombre();
	}
	
	boolean validaFecha() {
		return modelo.validaFecha();
	}
	
	public void iniciarPrograma() {
		vista.setVisible(true);
		vista.setModeloTabla(modelo.getModeloTabla());
	}

	public void guardarArchivo() {
		modelo.guardarArchivo();
	}



}
