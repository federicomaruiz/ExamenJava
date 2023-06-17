/**
 * 
 */
package examen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author federicoruiz 15 jun 2023 11:38:54
 */
public class Vista extends JFrame {

	private Modelo modelo;
	private Controlador controlador;
	private JPanel contentPane;
	private JTable tabla;
	private JTextField txtNombre;
	private JTextField txtAño;
	private JButton btnEnviar;
	private Boolean comprobarNombre = false;
	private JLabel lblNombreErr;
	private JLabel lblEdadMedia;
	private JTextField txtEdadMedia;
	private Boolean comprobarEdad = false;
	private JLabel lblAñoErr;
	private JLabel lblLetraRep;
	private JTextField txtLetraRep;

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
		scrollPane.setBounds(32, 6, 412, 159);
		contentPane.add(scrollPane);

		tabla = new JTable();
		scrollPane.setViewportView(tabla);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				comprobarNombre = controlador.validaNombre();
				if (comprobarNombre) {
					lblNombreErr.setVisible(false);
				} else {
					lblNombreErr.setVisible(true);
				}
				if (comprobarNombre && comprobarEdad) {
					btnEnviar.setEnabled(true);
				}else {
					btnEnviar.setEnabled(false);
				}
			}
		});
		txtNombre.setBounds(163, 199, 130, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtAño = new JTextField();
		txtAño.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				comprobarEdad = controlador.comprobarEdad();
				if (comprobarEdad) {
					lblAñoErr.setVisible(false);
				} else {
					lblAñoErr.setVisible(true);
				}
				if (comprobarNombre && comprobarEdad) {
					btnEnviar.setEnabled(true);
				}else {
					btnEnviar.setEnabled(false);
				}
			}
		});
		txtAño.setBounds(163, 237, 130, 26);
		contentPane.add(txtAño);
		txtAño.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(32, 204, 78, 16);
		contentPane.add(lblNombre);

		JLabel lblAño = new JLabel("Año de nacimiento");
		lblAño.setBounds(35, 242, 130, 16);
		contentPane.add(lblAño);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setEnabled(false);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cargarDatos();
				float edad = controlador.calcularEdad();
				String edadS = String.valueOf(edad);
				txtEdadMedia.setText(edadS);
				char letra = controlador.letraRepetida();
				String letraS = String.valueOf(letra);
				txtLetraRep.setText(letraS);

			}
		});
		btnEnviar.setBounds(353, 171, 78, 29);
		contentPane.add(btnEnviar);

		lblNombreErr = new JLabel("Error solo letras");
		lblNombreErr.setVisible(false);
		lblNombreErr.setBounds(305, 204, 125, 16);
		contentPane.add(lblNombreErr);

		lblEdadMedia = new JLabel("Edad Media");
		lblEdadMedia.setBounds(32, 176, 78, 16);
		contentPane.add(lblEdadMedia);

		txtEdadMedia = new JTextField();
		txtEdadMedia.setBounds(110, 171, 41, 26);
		contentPane.add(txtEdadMedia);
		txtEdadMedia.setColumns(10);

		lblAñoErr = new JLabel("Solo 4 numeros");
		lblAñoErr.setVisible(false);
		lblAñoErr.setBounds(305, 242, 114, 16);
		contentPane.add(lblAñoErr);
		
		lblLetraRep = new JLabel("Letra mas repetida");
		lblLetraRep.setBounds(176, 177, 130, 16);
		contentPane.add(lblLetraRep);
		
		txtLetraRep = new JTextField();
		txtLetraRep.setColumns(10);
		txtLetraRep.setBounds(305, 171, 41, 26);
		contentPane.add(txtLetraRep);
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

	public void ActualizarDatos(DefaultTableModel miTabla) {
		tabla.setModel(miTabla);
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
	 * @return the txtAño
	 */
	public JTextField getTxtAño() {
		return txtAño;
	}

	/**
	 * @param txtAño the txtAño to set
	 */
	public void setTxtAño(JTextField txtAño) {
		this.txtAño = txtAño;
	}

	/**
	 * @return the btnEnviar
	 */
	public JButton getBtnEnviar() {
		return btnEnviar;
	}

}
