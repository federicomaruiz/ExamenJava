/**
 * 
 */
package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 * @author federicoruiz 15 jun 2023 11:46:44
 * 
 *         COMENTARIO !! No lo hice con ficheros voy hacer con base de datos
 * 
 * 
 *         create database users; use users; create table nombres( Nombre
 *         varchar(30), AñoNacimiento int );
 *         create table nombres(
			Nombre varchar(30),
			AñoNacimiento int
			);
 * 
 * 
 * 
 */
public class Modelo extends Info implements Gestionar {

	private Vista vista;
	private String user = "root";
	private String pwd = "";
	private String DB = "users";
	private String URL = "jdbc:mysql://localhost/" + DB;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection conexion;
	private String sql = "Select * from nombres";
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private DefaultTableModel miTabla;

	//private String nombre;
	//private int añoNacimiento;

	/*
	 * Hago la conexion a la base de datos pinto la tabla añado los nombres de las
	 * cabeceras y luego las columnas
	 *  
	 */

	Modelo() {
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(URL, user, pwd);
			System.out.println("Conexion establecida");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de conexion");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Eror en SQL");
			e.printStackTrace();
		}
	}

	public void cargarDatos() {
		miTabla = new DefaultTableModel();
		int numeroColum = getColum(sql);
		Object[] contenido = new Object[numeroColum];
		try {
			ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			for (int i = 0; i < numeroColum; i++) {
				miTabla.addColumn(rsmd.getColumnName(i + 1));
			}
			while (rs.next()) {
				for (int i = 0; i < numeroColum; i++) {
					contenido[i] = rs.getString(i + 1);
				}
				miTabla.addRow(contenido);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vista.ActualizarDatos(miTabla);
	}

	/**
	 * 
	 * Cuento la cantidad de columnas que tiene mi tabla
	 */
	public int getColum(String sql) {
		int resultado = 0;
		try {
			ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			resultado = rsmd.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
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

	/**
	 * @param user2
	 * @param año
	 * 
	 *              Añado el usuario a la base de datos y a mis listas para luego
	 *              sacar la media de edad y la inicial mas usuada.
	 */
	public int añadir(String user2, String año) {
		int res = 0;
		String sqlInsert = "INSERT into nombres set Nombre=? , AñoNacimiento=? ";
		int añoInt = Integer.parseInt(año);
		try {
			ps = conexion.prepareStatement(sqlInsert);
			ps.setString(1, user2);
			ps.setLong(2, añoInt);
			res = ps.executeUpdate();
			System.out.println("Añadido");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error el usuario no fue añadido");
		}
		return res;
	}
	/**
	 * @return the miTabla
	 */
	public DefaultTableModel getMiTabla() {
		return miTabla;
	}

	@Override
	public float getEdadMedia(Object[] miLista) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char getMasUsada(Object[] miLista) {
	
		return 0;
	}

	@Override
	public boolean validaNombre() {
		// TODO Auto-generated method stub
		return false;
	}	
}
