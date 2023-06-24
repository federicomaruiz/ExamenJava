/**
 * 
 */
package crud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author federicoruiz 21 jun 2023 15:16:13
 */
public class Modelo extends Info implements Gestionar {

	Vista vista;
	Vfichero Vfichero;
	private String user = "root";
	private String pwd = "";
	private String Db = "users";
	private String Url = "jdbc:mysql://localhost/" + Db;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection conexion;
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private String sql = "SELECT * FROM nombres";
	private DefaultTableModel miTabla;
	private final String ARCHIVO = "archivo.csv";

	/*
	 * Hago la conexion a la base de datos si tengo algun error y no me puedo
	 * conectar no tiene sentido la aplicacion muestro mensaje de error y se cierra
	 * la app en tres segundos (para que el usuario vea el mensaje)
	 */
	public void conexion() {
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(Url, user, pwd);
		} catch (ClassNotFoundException | SQLException e) {
			vista.errorFatal();
			e.printStackTrace();
		}
		cargarTabla();
	}

	/**
	 * Cargo la tabla de la base de datos usando la la query que tengo guardada en
	 * la variable SQL Primero añado los nombres de las cabeceras que se encuentran
	 * en los meta datos luego ingreso los valores de los campos que se encuentran
	 * en ResultSet (siempre i+1 para empezar por la posicion 1) Luego llamo al
	 * metodo para actualizar mi tabla. Cualquier error tambien es considerado
	 * fatal, cierre de app.
	 */
	public void cargarTabla() {
		miTabla = new DefaultTableModel();
		int numColum = getNumColum(sql);
		Object[] contenido = new Object[numColum];
		try {
			ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			for (int i = 0; i < numColum; i++) {
				miTabla.addColumn(rsmd.getColumnName(i + 1));
			}
			while (rs.next()) {
				for (int i = 0; i < numColum; i++) {
					contenido[i] = rs.getString(i + 1);
				}
				miTabla.addRow(contenido);
			}
		} catch (SQLException e) {
			vista.errorFatal();
			e.printStackTrace();
		}
		vista.getTabla().setModel(miTabla);
	}

	/**
	 * @param sql2
	 * @return numColumnas
	 * 
	 *         Voy a sacar el numero de columnas que va a tener mi tabla cualquier
	 *         error lo considero fatal cierro la app.
	 */
	private int getNumColum(String sql2) {
		int numColumnas = 0;
		try {
			ps = conexion.prepareStatement(sql2);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			numColumnas = rsmd.getColumnCount();
		} catch (SQLException e) {
			vista.errorFatal();
			e.printStackTrace();
		}

		return numColumnas;
	}

	@Override
	public float getEdadMedia(Object[] miLista) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char getMasUsada(Object[] miLista) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	boolean validaNombre() {
		// TODO Auto-generated method stub
		return false;
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
	 * @return the vfichero
	 */
	public Vfichero getVfichero() {
		return Vfichero;
	}

	/**
	 * @param vfichero the vfichero to set
	 */
	public void setVfichero(Vfichero vfichero) {
		Vfichero = vfichero;
	}

	/**
	 * 
	 */
	public void guardarFichero() {
		int filas = Vfichero.gettModel().getRowCount();
		File file = new File(ARCHIVO);
		FileWriter fw;

		try {
			fw = new FileWriter(file,true);
			for (int i = 0; i < filas; i++) {
				String nombre = (String) Vfichero.gettModel().getValueAt(i, 0);
				String data = (String) Vfichero.gettModel().getValueAt(i, 1);
				fw.write(String.format(nombre + " " + data + "\n "));
				System.out.println(String.format(nombre + " " + data));
			}
			fw.flush();
			fw.close();
			JOptionPane.showMessageDialog(null, "Fichero guardado");

		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Error escribiendo al fichero");
			return;
		}
	}

	/**
	 * 
	 */
	
		public void añadirDatos() {
		
		    // Obtener nuevos valores ingresados por el usuario desde un JTextField
		    String nombreIngresado = Vfichero.getTxtNombre().getText();
		    String datoIngresado = Vfichero.getTxtDato().getText();

		    // Agregar los nuevos valores al modelo
		    Object[] nuevaFila = {nombreIngresado, datoIngresado};
		    Vfichero.gettModel().addRow(nuevaFila);

		    // Actualizar la tabla con todos los datos
		    Vfichero.getTable().setModel(Vfichero.gettModel());
		}

	/**
	 * @param edad 
	 * @param nombre 
	 * @return
	 */
	public int insertarUsuario(String nombre, int edad) {
		int respuesta = 0;
		String sqlInsert = "INSERT into nombres set Nombre=? , AnoNacimiento=? ";
		try {
			ps = conexion.prepareStatement(sqlInsert);
			ps.setString(1, nombre);
			ps.setLong(2, edad);
			respuesta = ps.executeUpdate();
			vaciarCampos();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Eror en sentencia SQL");
			e.printStackTrace();
		}
		return respuesta;
	}
	
	public void vaciarCampos(){
		
		vista.getTxtEdad().setText("");
		vista.getTxtNombre().setText("");
		
	}

	/**
	 * @param edad 
	 * @param nombreFormateado 
	 * 
	 */
	public int eliminar(String nombreFormateado, int edad) {
		int respuesta = 0;
		String sqlDelete = "DELETE FROM nombres WHERE nombre=? AND AnoNacimiento=?";
		try {
			ps = conexion.prepareStatement(sqlDelete);
			ps.setString(1, nombreFormateado);
			ps.setLong(2, edad);
			respuesta = ps.executeUpdate();
			vaciarCampos();
		} catch (SQLException e) {
			vista.errorFatal();
			e.printStackTrace();
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
		int respuesta = 0;
		String sqlUpdate = "UPDATE nombres SET Nombre=?, AnoNacimiento=? WHERE Nombre='"+ usuarioAModificar   +"'";
		try {
			ps = conexion.prepareStatement(sqlUpdate);
			ps.setString(1, nombreFormateado);
			ps.setLong(2, edad);
			respuesta = ps.executeUpdate();
			vaciarCampos();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en setencia SQL");
			e.printStackTrace();
		}
		return respuesta;
	}

}
