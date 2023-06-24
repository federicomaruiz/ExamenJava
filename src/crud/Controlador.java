/**
 * 
 */
package crud;

/**
 * @author federicoruiz
 * 21 jun 2023 15:16:23
 */
public class Controlador {

	Vista vista;
	Modelo modelo;
	Vfichero vFichero;
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
	
	public void iniciarApp() {
		modelo.conexion();
	}
	/**
	 * @return the vFichero
	 */
	public Vfichero getvFichero() {
		return vFichero;
	}
	/**
	 * @param vFichero the vFichero to set
	 */
	public void setvFichero(Vfichero vFichero) {
		this.vFichero = vFichero;
	}
	/**
	 * @param edad 
	 * @param nombre 
	 * 
	 */
	public int insertarUsuario(String nombre, int edad) {
		int resultado = modelo.insertarUsuario(nombre,edad);
		if(resultado > 0) {
			modelo.cargarTabla();
		}
		return resultado;
	}
	/**
	 * @param nombreFormateado
	 */
	public boolean validarNombre(String nom) {
		boolean respuesta = false;
		if(nom.matches("[a-zA-ZÑñáéíóúÁÉÍÓÚ\s]+")) {
			respuesta = true;
		}
		return respuesta;
	}
	/**
	 * @param edad
	 * @return
	 */
	public boolean validarEdad(String edad) {
		if(edad.matches("[0-9]{4}")){
			int edadFormateada = Integer.parseInt(edad);
			System.out.println(edadFormateada);
			if((edadFormateada > 1900) && (edadFormateada < 2023)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @param edad 
	 * @param nombreFormateado 
	 * 
	 */
	public int eliminar(String nombreFormateado, int edad) {
		int respuesta = modelo.eliminar(nombreFormateado,edad);
		if(respuesta > 0) {
			modelo.cargarTabla();
		}
		return respuesta;
	}
	/**
	 * @param nombreFormateado
	 * @param edad
	 * @param usuarioModificar 
	 * @return
	 */
	public int modificar(String nombreFormateado, int edad, String usuarioAModificar) {
		int res = modelo.modificar(nombreFormateado,edad,usuarioAModificar);
		if(res > 0) {
			modelo.cargarTabla();
		}
		return res;
	}
	/**
	 * 
	 */
	public void cambioVista() {
		vista.setVisible(true);
		vFichero.setVisible(false);
		
	}
	
	
	
}
