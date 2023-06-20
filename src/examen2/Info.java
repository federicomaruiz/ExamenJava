/**
 * 
 */
package examen2;

/**
 * @author federicoruiz
 * 19 jun 2023 16:44:08
 */
public abstract class Info {

	private String nombre;
	private String anoNacimiento;
	abstract boolean validaNombre();
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the anoNacimiento
	 */
	public String getAnoNacimiento() {
		return anoNacimiento;
	}
	/**
	 * @param anoNacimiento the anoNacimiento to set
	 */
	public void setAnoNacimiento(String anoNacimiento) {
		this.anoNacimiento = anoNacimiento;
	}
	

}
