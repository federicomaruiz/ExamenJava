package examenFichero;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame {

	private JPanel contentPane;
	private Modelo modelo;
	private Controlador controlador;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtFecha;
	private JButton btnValidar;
	private JLabel lblAlertaFecha;
	private JLabel lblAlertaNombre;
	private JLabel lblAlertaEdadMedia;
	private JLabel lblAlertaLetra;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 438, 136);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(6, 155, 61, 16);
		contentPane.add(lblNewLabel);

		JLabel lblFnacimiento = new JLabel("F.Nacimiento:");
		lblFnacimiento.setBounds(6, 183, 101, 16);
		contentPane.add(lblFnacimiento);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validarBoton();
			}
		});
		txtNombre.setBounds(119, 150, 130, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validarBoton();
			}
		});
		txtFecha.setColumns(10);
		txtFecha.setBounds(119, 178, 130, 26);
		contentPane.add(txtFecha);

		btnValidar = new JButton("Validar");
		btnValidar.setEnabled(false);
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.agregaRegistro(txtNombre.getText(), txtFecha.getText());
				btnValidar.setEnabled(false);
			}
		});
		btnValidar.setBounds(327, 206, 117, 29);
		contentPane.add(btnValidar);

		lblAlertaNombre = new JLabel("Solo caracteres");
		lblAlertaNombre.setBounds(261, 154, 183, 16);
		contentPane.add(lblAlertaNombre);

		lblAlertaFecha = new JLabel("Desde 1900 a actualidad");
		lblAlertaFecha.setBounds(261, 183, 183, 16);
		contentPane.add(lblAlertaFecha);

		JLabel lblNewLabel_1 = new JLabel("Edad Media:");
		lblNewLabel_1.setBounds(6, 211, 87, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Letra repetida:");
		lblNewLabel_1_1.setBounds(6, 239, 101, 16);
		contentPane.add(lblNewLabel_1_1);

		lblAlertaEdadMedia = new JLabel("");
		lblAlertaEdadMedia.setForeground(new Color(255, 2, 0));
		lblAlertaEdadMedia.setBounds(119, 211, 87, 16);
		contentPane.add(lblAlertaEdadMedia);

		lblAlertaLetra = new JLabel("");
		lblAlertaLetra.setForeground(new Color(255, 2, 0));
		lblAlertaLetra.setBounds(119, 239, 87, 16);
		contentPane.add(lblAlertaLetra);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.guardarArchivo();
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(327, 234, 117, 29);
		contentPane.add(btnGuardar);
	}

	protected void validarBoton() {
		if (controlador.validaNombre() && controlador.validaFecha()) btnValidar.setEnabled(true);
		else btnValidar.setEnabled(false);
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloTabla(DefaultTableModel modeloTabla) {
		this.table.setModel(modeloTabla);
	}

	public String getTxtNombre() {
		return txtNombre.getText();
	}

	public String getTxtFecha() {
		return txtFecha.getText();
	}

	public void setEdadMedia(float edadMedia) {
		this.lblAlertaEdadMedia.setText(String.format("%.2f", edadMedia));
	}

	public void setMasUsada(char masUsada) {
		this.lblAlertaLetra.setText(String.valueOf(masUsada));
	}

	public void limpiarPantalla() {
		if ( lblAlertaEdadMedia.getText() != "" && lblAlertaLetra.getText() != "") btnGuardar.setEnabled(true);
		else btnGuardar.setEnabled(false);		
		txtFecha.setText(null);
		txtNombre.setText(null);
	}

	public Object getLblAlertaLetra() {
		return lblAlertaLetra.getText();
	}

	public Object getLblAlertaEdadMedia() {
		return lblAlertaEdadMedia.getText();
	}
}
