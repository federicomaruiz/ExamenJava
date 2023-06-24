/**
 * 
 */
package probando;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

/**
 * @author federicoruiz
 * 20 jun 2023 19:10:01
 */
public class Modelo {
	
	DefaultTableModel miTabla;
	ArrayList<String> nombres = new ArrayList<>();
	ArrayList<Integer> edades = new ArrayList<>();
	String [] cabecera = {"Nombre", "Edades"};
	Vista vista;
	public void cargarTabla() {
	
		nombres.add("Marco");
		edades.add(20);
		Object [] lista = { nombres.get(0),edades.get(0)};
		
		miTabla = new DefaultTableModel( new Object[][]{ {"hola", "que tal"}},cabecera);
		
		vista.getTabla().setModel(miTabla);
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
	
	
	
}
