/**
 * 
 */
package examen2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 * @author federicoruiz
 * 19 jun 2023 16:42:21
 */
public class Modelo {

	private Vista vista;
	private String user = "root";
	private String pwd= "";
	private String DB = "users";
	private String URl = "jdbc:mysql://localhost/" + DB;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection conexion;
	private String sql = "SELECT * FROM nombres";
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private DefaultTableModel miTabla;

	
	/*
	 * Conexion a base de datos cualquier error por el cual no se pueda conectar 
	 * a la base de datos lo voy a conciderar error fatal y voy a cerrar la aplicacion
	 * */
	public void CargarDb() {
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(URl, user, pwd);
		} catch (ClassNotFoundException | SQLException e) {
			vista.errorFatal();
			e.printStackTrace();
		}
		cargarTabla();
	}
	
	/*
	 * Cargo la tabla que tengo en mi base de datos y la pinto en mi Vista.
	 * Cualquier error que pueda ocurrir cierro la aplicacion ya que no tiene sentido sin los datos.
	 * */
	public void cargarTabla() {
		miTabla = new DefaultTableModel();
		int nColumnas = getColumn(sql);
		Object [] contenido = new Object[nColumnas];
		
		try {
			ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			for (int i = 0; i < nColumnas; i++) {
				miTabla.addColumn(rsmd.getColumnName(i+1));
			}
			while(rs.next()) {
				for (int i = 0; i < nColumnas; i++) {
					contenido[i]= rs.getString(i+1);
				}
				miTabla.addRow(contenido);
			}
		} catch (SQLException e) {
			vista.errorFatal();
			e.printStackTrace();
		}
		vista.actualizarTabla(miTabla);
	}
	
	/*
	 * Saco el numero de columnas cualquier error que se produzca que no pueda recuperar el numero
	 * de columnas lo considerare fatal y cerrare la aplicacion
	 * */
	public int getColumn (String sql) {
		int numeroColum = 0;
		try {
			ps = conexion.prepareStatement(sql);
			rs =  ps.executeQuery();
			rsmd = rs.getMetaData();
			numeroColum = rsmd.getColumnCount();
		} catch (SQLException e) {
			vista.errorFatal();
			e.printStackTrace();
		}
		return numeroColum;
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
	 * Añado el usuario a la base de datos , cualquier error ciero la aplicacion
	 * @return 1 si se pudo añadir 0 sino
	 */
	public int cargarUsuario() {
		int resultado = 0;
		String usuario = vista.getTxtNombre().getText();
		int ano = Integer.parseInt(vista.getTxtAño().getText());
		String sqlInsert = "INSERT INTO nombres SET nombre=?, anoNacimiento=? ";
		try {
			ps = conexion.prepareStatement(sqlInsert);
			ps.setString(1, usuario);
			ps.setLong(2, ano);
			resultado = ps.executeUpdate();
		} catch (SQLException e ) {
			vista.errorFatal();
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * @return the miTabla
	 */
	public DefaultTableModel getMiTabla() {
		return miTabla;
	}
	
	

	
	

}
