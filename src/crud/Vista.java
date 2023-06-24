/**
 * 
 */
package crud;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author federicoruiz 21 jun 2023 15:16:47
 */
public class Vista extends JFrame {

	private JPanel contentPane;
	Vfichero Vfichero;
	Modelo modelo;
	Controlador controlador;
	private JTable tabla;
	private JButton btnCambio;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private boolean nombreValido = false;
	private boolean edadValido = false;
	private JLabel lblNombreErr;
	private JLabel lblEdadErr;
	private String usuarioAModificar;
	private JButton btnModificar;
	private JButton btnEliminar;


	/**
	 * Create the frame.
	 */
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 20, 374, 133);
		contentPane.add(scrollPane);

		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("entro");
				int fila = tabla.getSelectedRow();
				String nombre = (String) tabla.getValueAt(fila,0);
				String edad = (String) tabla.getValueAt(fila,1);
				usuarioAModificar = nombre; // Almaceno el usuario a modificar
				txtNombre.setText(nombre);
				txtEdad.setText(edad);
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
				
			}
		});
		scrollPane.setViewportView(tabla);
		
		btnCambio = new JButton("Cambio de vista");
		btnCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vfichero.setVisible(true);
				setVisible(false);
			}
		});
		btnCambio.setBounds(32, 165, 166, 29);
		contentPane.add(btnCambio);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(38, 211, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Edad :");
		lblEdad.setBounds(38, 245, 61, 16);
		contentPane.add(lblEdad);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre = txtNombre.getText().toLowerCase().trim();
				nombreValido = controlador.validarNombre(nombre);
				if(!nombreValido) {
					lblNombreErr.setVisible(true);
				}else {
					lblNombreErr.setVisible(false);
				}
			}
		});
		txtNombre.setBounds(111, 206, 130, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtEdad = new JTextField();
		txtEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String edad = (String)txtEdad.getText().trim();
				edadValido = controlador.validarEdad(edad);
				if(!edadValido) {
					lblEdadErr.setVisible(true);
				}else {
					lblEdadErr.setVisible(false);
				}
			}
		});
		txtEdad.setColumns(10);
		txtEdad.setBounds(111, 240, 130, 26);
		contentPane.add(txtEdad);
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = (String)getTxtNombre().getText().toLowerCase().trim(); // Formateo las entradas
				String nombreFormateado = Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1).toLowerCase();
				int edad = Integer.parseInt(getTxtEdad().getText().trim());
				int respuesta = controlador.insertarUsuario(nombreFormateado,edad); // LLamo al controlador para añadir los datos guardo la repsuesta
				if(respuesta > 0) {
					JOptionPane.showMessageDialog(null, "Usuario añadido"); // Si me devuelve mayor que 0 usuario añadido sino , no se añadio
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo añadir el usuario");
				}
			}
		});
		btnGuardar.setBounds(327, 165, 117, 29);
		contentPane.add(btnGuardar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = (String)getTxtNombre().getText().toLowerCase().trim();
				String nombreFormateado = Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1).toLowerCase();
				int edad = Integer.parseInt(getTxtEdad().getText().trim());
				int respuesta = controlador.modificar(nombreFormateado,edad,usuarioAModificar);
				if(respuesta > 0) {
					JOptionPane.showMessageDialog(null, "Usuario actualizado");
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo actualizar el usuario");
				}
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
			}
		});
		btnModificar.setBounds(327, 206, 117, 29);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = (String)getTxtNombre().getText().toLowerCase().trim(); // Formateo las entradas
				String nombreFormateado = Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1).toLowerCase();
				int edad = Integer.parseInt(getTxtEdad().getText().trim());
				int respuesta = controlador.eliminar(nombreFormateado,edad);
				if(respuesta > 0) {
					JOptionPane.showMessageDialog(null, "Usuario eliminado");
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario");
				}
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
			}
		});
		btnEliminar.setBounds(327, 240, 117, 29);
		contentPane.add(btnEliminar);
		
		lblNombreErr = new JLabel("Solo letras");
		lblNombreErr.setVisible(false);
		lblNombreErr.setBounds(253, 211, 73, 16);
		contentPane.add(lblNombreErr);
		
		lblEdadErr = new JLabel("Años de 1901-2022");
		lblEdadErr.setVisible(false);
		lblEdadErr.setBounds(254, 245, 61, 16);
		contentPane.add(lblEdadErr);
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

	/**
	 * @return the controlador
	 */
	public Controlador getControlador() {
		return controlador;
	}

	/**
	 * @param controlador the controlador to set
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * @param errorMessage 
	 * 
	 */
	public void errorFatal() {
		JOptionPane.showMessageDialog(null, "Error cerrando aplicacion...");
		setVisible(false);
	}

	/**
	 * @return the tabla
	 */
	public JTable getTabla() {
		return tabla;
	}

	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
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
	 * @return the btnCambio
	 */
	public JButton getBtnCambio() {
		return btnCambio;
	}

	/**
	 * @param btnCambio the btnCambio to set
	 */
	public void setBtnCambio(JButton btnCambio) {
		this.btnCambio = btnCambio;
	}

	/**
	 * @return the txtNombre
	 */
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	/**
	 * @param txtNombre the txtNombre to set
	 */
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	/**
	 * @return the txtEdad
	 */
	public JTextField getTxtEdad() {
		return txtEdad;
	}

	/**
	 * @param txtEdad the txtEdad to set
	 */
	public void setTxtEdad(JTextField txtEdad) {
		this.txtEdad = txtEdad;
	}
	
	
}
