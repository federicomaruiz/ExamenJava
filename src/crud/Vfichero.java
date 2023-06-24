/**
 * 
 */
package crud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
 * @author federicoruiz
 * 22 jun 2023 11:44:00
 */
public class Vfichero extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Modelo modelo;
	private Controlador controlador;
	private Vista vista;
	private DefaultTableModel tModel;
	private JTextField txtNombre;
	private JTextField txtDato;
	

	/**
	 * Create the frame.
	 */
	public Vfichero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 17, 373, 131);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setRowHeaderView(table);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.añadirDatos();
				modelo.guardarFichero();
				txtNombre.setText("");
				txtDato.setText("");
			}
		});
		btnGuardar.setBounds(293, 223, 117, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioVista();
			}
		});
		btnCambiar.setBounds(30, 223, 117, 29);
		contentPane.add(btnCambiar);
		
		
		 tModel=new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"Nombre", "Dato"});
		 
	
		 String [] contenido = {"pepe","lava coches"};
		 tModel.addRow(contenido);
		 
		 table.setModel(tModel);
		 scrollPane.setViewportView(table); 
		 
		 txtNombre = new JTextField();
		 txtNombre.setBounds(37, 175, 130, 26);
		 contentPane.add(txtNombre);
		 txtNombre.setColumns(10);
		 
		 txtDato = new JTextField();
		 txtDato.setColumns(10);
		 txtDato.setBounds(212, 175, 130, 26);
		 contentPane.add(txtDato);
			
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
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the tModel
	 */
	public DefaultTableModel gettModel() {
		return tModel;
	}

	/**
	 * @param tModel the tModel to set
	 */
	public void settModel(DefaultTableModel tModel) {
		this.tModel = tModel;
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
	 * @return the txtDato
	 */
	public JTextField getTxtDato() {
		return txtDato;
	}

	/**
	 * @param txtDato the txtDato to set
	 */
	public void setTxtDato(JTextField txtDato) {
		this.txtDato = txtDato;
	}
	
	
}
