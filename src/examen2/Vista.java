/**
 * 
 */
package examen2;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author federicoruiz 19 jun 2023 16:42:41
 */
public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private JTextField txtNombre;
	private JTextField txtAno;
	private JTextField txtEdadMedia;
	private JTextField txtLetraRep;
	private JLabel lblNombreErr;
	private JLabel lblAnoErr;
	private JButton btnCargar;
	private Modelo modelo;
	private Controlador controlador;
	private JLabel lblErrorConexion;
	private boolean nombreValido = false;
	private boolean anoValido = false;
	private JLabel lblUsuarioAnadido;
	private JLabel lblUsuarioAnadidoErr;

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
		scrollPane.setBounds(27, 6, 398, 146);
		contentPane.add(scrollPane);

		tabla = new JTable();
		scrollPane.setViewportView(tabla);

		JLabel lblEdadMedia = new JLabel("Edad media:");
		lblEdadMedia.setBounds(27, 173, 94, 16);
		contentPane.add(lblEdadMedia);

		JLabel lblLetraMasRepetida = new JLabel("Letra mas repetida:");
		lblLetraMasRepetida.setBounds(192, 173, 128, 16);
		contentPane.add(lblLetraMasRepetida);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(27, 214, 64, 16);
		contentPane.add(lblNombre);

		JLabel lblEdadMedia_1_1 = new JLabel("Año:");
		lblEdadMedia_1_1.setBounds(27, 242, 53, 16);
		contentPane.add(lblEdadMedia_1_1);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				nombreValido = controlador.validaNombre();
				if (nombreValido) {
					lblNombreErr.setVisible(false);
				} else {
					lblNombreErr.setVisible(true);
				}
				if (nombreValido && anoValido) {
					btnCargar.setEnabled(true);
				} else {
					btnCargar.setEnabled(false);
				}
			}
		});
		txtNombre.setBounds(93, 209, 130, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtAno = new JTextField();
		txtAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				anoValido = controlador.validarAno();
				if (anoValido) {
					lblAnoErr.setVisible(false);
				} else {
					lblAnoErr.setVisible(true);
				}
				if (nombreValido && anoValido) {
					btnCargar.setEnabled(true);
				} else {
					btnCargar.setEnabled(false);
				}
			}
		});
		txtAno.setColumns(10);
		txtAno.setBounds(92, 237, 130, 26);
		contentPane.add(txtAno);

		txtEdadMedia = new JTextField();
		txtEdadMedia.setBounds(107, 168, 43, 26);
		contentPane.add(txtEdadMedia);
		txtEdadMedia.setColumns(10);

		txtLetraRep = new JTextField();
		txtLetraRep.setColumns(10);
		txtLetraRep.setBounds(320, 168, 43, 26);
		contentPane.add(txtLetraRep);

		lblNombreErr = new JLabel("Solo letras");
		lblNombreErr.setForeground(Color.RED);
		lblNombreErr.setVisible(false);
		lblNombreErr.setBounds(235, 214, 108, 16);
		contentPane.add(lblNombreErr);

		lblAnoErr = new JLabel("Solo 4 numeros");
		lblAnoErr.setForeground(Color.RED);
		lblAnoErr.setVisible(false);
		lblAnoErr.setBounds(234, 242, 108, 16);
		contentPane.add(lblAnoErr);

		btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cargarUsuario();
				controlador.edadMedia();
				controlador.letraMasUsada();
				txtNombre.setText("");
				txtAno.setText("");
			}
		});
		btnCargar.setEnabled(false);
		btnCargar.setBounds(342, 237, 94, 29);
		contentPane.add(btnCargar);

		lblErrorConexion = new JLabel("ERROR, CERRANDO APLICACION");
		lblErrorConexion.setVisible(false);
		lblErrorConexion.setForeground(Color.RED);
		lblErrorConexion.setBounds(68, 155, 295, 16);
		contentPane.add(lblErrorConexion);

		lblUsuarioAnadido = new JLabel("Usuario añadido");
		lblUsuarioAnadido.setVisible(false);
		lblUsuarioAnadido.setBounds(120, 155, 140, 16);
		contentPane.add(lblUsuarioAnadido);

		lblUsuarioAnadidoErr = new JLabel("No se pudo añadir el usuario");
		lblUsuarioAnadidoErr.setForeground(Color.RED);
		lblUsuarioAnadidoErr.setVisible(false);
		lblUsuarioAnadidoErr.setBounds(235, 201, 188, 16);
		contentPane.add(lblUsuarioAnadidoErr);
	}

	/**
	 * @return the btnCargar
	 */
	public JButton getBtnCargar() {
		return btnCargar;
	}

	/**
	 * @param btnCargar the btnCargar to set
	 */
	public void setBtnCargar(JButton btnCargar) {
		this.btnCargar = btnCargar;
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
	 * @return the tabla
	 */
	public JTable getTabla() {
		return tabla;
	}

	/**
	 * @return the lblNombreErr
	 */
	public JLabel getLblNombreErr() {
		return lblNombreErr;
	}

	/**
	 * @return the lblAñoErr
	 */
	public JLabel getLblAñoErr() {
		return lblAnoErr;
	}

	/**
	 * @return the txtEdadMedia
	 */
	public JTextField getTxtEdadMedia() {
		return txtEdadMedia;
	}

	/**
	 * @param txtEdadMedia the txtEdadMedia to set
	 */
	public void setTxtEdadMedia(JTextField txtEdadMedia) {
		this.txtEdadMedia = txtEdadMedia;
	}

	/**
	 * @return the txtLetraRep
	 */
	public JTextField getTxtLetraRep() {
		return txtLetraRep;
	}

	/**
	 * @param txtLetraRep the txtLetraRep to set
	 */
	public void setTxtLetraRep(JTextField txtLetraRep) {
		this.txtLetraRep = txtLetraRep;
	}

	/**
	 * @return the txtNombre
	 */
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	/**
	 * @return the txtAño
	 */
	public JTextField getTxtAño() {
		return txtAno;
	}

	/**
	 * 
	 */
	public void errorFatal() {
		lblErrorConexion.setVisible(true);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.exit(0); // Cierra la aplicación después de 3 segundos
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 3000);
	}

	/**
	 * @param miTabla
	 */
	public void actualizarTabla(DefaultTableModel miTabla) {
		tabla.setModel(miTabla);

	}

	/**
	 * @return the lblUsuarioAnadido
	 */
	public JLabel getLblUsuarioAnadido() {
		return lblUsuarioAnadido;
	}

	/**
	 * @return the lblUsuarioAnadidoErr
	 */
	public JLabel getLblUsuarioAnadidoErr() {
		return lblUsuarioAnadidoErr;
	}

}
