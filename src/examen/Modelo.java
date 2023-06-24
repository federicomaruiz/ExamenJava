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
 *         create database users;
 *         use users; 
 *         create table nombres( Nombre
 *         varchar(30), AñoNacimiento int );
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
	 * Añado el usuario a mi base de datos
	 */
	public int añadir(String user2, String año) {
		super.anoNacimiento = Integer.parseInt(año);
		super.nombre = user2;
		int res = 0;
		String sqlInsert = "INSERT into nombres set Nombre=? , AnoNacimiento=? ";
		try {
			ps = conexion.prepareStatement(sqlInsert);
			ps.setString(1, super.nombre);
			ps.setLong(2, super.anoNacimiento);
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
	/*
	 * Recorro el array y lo voy acumulando en total, luego divido el total por la 
	 * cantidad de personas y le resto el año actual, obtengo la media de edad
	 * */
	@Override
	public float getEdadMedia(Object[] miLista) {
		int total = 0;
		float respuesta;
		for (Object object : miLista) {
			total += Integer.parseInt((String) object);
		}
		int cantidadPersonas = getMiTabla().getRowCount();
		int edadMedia = total / cantidadPersonas;
		respuesta = 2023 - edadMedia;
		return respuesta;
	}

	/*
	 * Recorro el array de objetos con dos bucles anidados , accedo al primer nombre
	 * miro la primera letra y la comparado con la de todos los nombres cuando
	 * encuentra dos letras repetidas count ++ , y la var masRep toma el valor de
	 * esa letra tambien modifico el maxCount para saber que de momento es la letra
	 * mas repetida. Asi sucesivamente hasta recorrer toda el array.
	 **/
	@Override
	public char getMasUsada(Object[] miLista) {
		int maxCount = 0;
		char masRep = '\0';

		for (Object elemento : miLista) {
			String nombre = (String) elemento;
			char primeraLetra = nombre.charAt(0);
			int count = 0;

			for (Object obj : miLista) {
				String otroNombre = (String) obj;
				if (otroNombre.charAt(0) == primeraLetra) {
					count++;
				}
			}

			if (count > maxCount) {
				maxCount = count;
				masRep = primeraLetra;
			}
		}
		return masRep;
	}

	/*
	 * Compruebo que el nombre introducio solo tengo letras del abecedario español y
	 * tambien pueda introducir esapcios en blanco
	 */
	@Override
	public boolean validaNombre() {
		String user = vista.getTxtNombre().getText();
		if (user.matches("[a-zA-Zá-úÁ-Ú\s]+")) {
			return true;
		} else {
			return false;
		}
	}
}
