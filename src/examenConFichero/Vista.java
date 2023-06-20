package examenConFichero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista {
	Controlador controlador;
	Modelo modelo;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	private JFrame frame;

	private JTextField textNombre;
	public JFrame getFrame() {
		return frame;
	}


	public void setLblMediaTxt(String media) {
		this.lblMedia.setText(media);
	}


	public void setLblMasUsadaTxt(String masUsada) {
		this.lblMasUsada.setText(masUsada);
	}

	public String getLblMediaTxt() {
		return(lblMedia.getText());
	}


	public String getLblMasUsadaTxt() {
		return(lblMasUsada.getText());
	}
	
	public void vaciaCampos() {
		textFecha.setText("");
		textNombre.setText("");
	}
	
	public void habilitaFich() {
		btnFichero.setEnabled(true);
	}
	

	private JTextField textFecha;
	private JTable table;
	private JLabel lblMedia;
	private JLabel lblMasUsada;
	public DefaultTableModel tModel=new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Nombre", "Nació"
			}
		);
	private JButton btnFichero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("OJO: EJECUTANDO MAIN DE VENTANA....");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(107, 32, 297, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(107, 63, 86, 20);
		frame.getContentPane().add(textFecha);
		textFecha.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(36, 35, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nació");
		lblNewLabel_1.setBounds(36, 66, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Edad media");
		lblNewLabel_2.setBounds(36, 196, 86, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Más Usada");
		lblNewLabel_3.setBounds(36, 221, 86, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblMedia = new JLabel("-");
		lblMedia.setBounds(147, 196, 46, 14);
		frame.getContentPane().add(lblMedia);
		
		lblMasUsada = new JLabel("-");
		lblMasUsada.setBounds(147, 221, 46, 14);
		frame.getContentPane().add(lblMasUsada);
		
		JButton btnAdd = new JButton("Añadir");
		btnAdd.setMnemonic('A');
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg;
				if((msg=controlador.add(textNombre.getText(),textFecha.getText()))!=null)
					JOptionPane.showMessageDialog(null, msg);
			}
		});
		btnAdd.setBounds(107, 109, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(234, 63, 170, 147);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(tModel);
		scrollPane.setViewportView(table);
		
		btnFichero = new JButton("Generar Fichero");
		btnFichero.setMnemonic('F');
		btnFichero.setEnabled(false);
		btnFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.generaFichero();
			}
		});
		btnFichero.setBounds(244, 227, 138, 23);
		frame.getContentPane().add(btnFichero);
	}
}
